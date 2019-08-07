package org.weframe.kotlinresourcesserver.purchase

import com.auth0.jwt.JWT
import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken
import com.mercadopago.resources.Payment
import com.mercadopago.resources.Preference
import com.mercadopago.resources.datastructures.preference.BackUrls
import com.mercadopago.resources.datastructures.preference.Item
import com.mercadopago.resources.datastructures.preference.Payer
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.Resources
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.weframe.kotlinresourcesserver.product.backboard.BackboardRepository
import org.weframe.kotlinresourcesserver.product.frame.Frame
import org.weframe.kotlinresourcesserver.product.frame.FrameRepository
import org.weframe.kotlinresourcesserver.product.mat.mattype.MatTypeRepository
import org.weframe.kotlinresourcesserver.product.picture.file.PictureFileService
import org.weframe.kotlinresourcesserver.product.picture.user.UserPicture
import org.weframe.kotlinresourcesserver.product.picture.user.UserPictureRepository
import java.security.Principal
import java.text.DecimalFormat
import java.time.Instant
import java.util.*
import javax.servlet.http.HttpServletResponse
import kotlin.math.roundToInt


@RestController
@RequestMapping("/purchases")
class PurchaseController(private val repo: PurchaseRepository,
                         private val frameRepository: FrameRepository,
                         private val backboardRepository: BackboardRepository,
                         private val userPictureRepository: UserPictureRepository,
                         private val matTypeRepository: MatTypeRepository,
                         private val pictureService: PictureFileService) {

    @Value(value = "\${mercado-pago.back.url.base}")
    private val backUrlBase: String? = null

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    fun create(@RequestBody purchase: Purchase, principal: Principal, response: HttpServletResponse) : ResponseEntity<Purchase> {
        val email = JWT.decode((principal as AuthenticationJsonWebToken).token).getClaim("https://email").asString()

        purchase.backboard = backboardRepository.findOne(purchase.backboard!!.id)
        purchase.userPicture = userPictureRepository.findOne(purchase.userPicture!!.id)
        purchase.frame = frameRepository.findOne(purchase.frame!!.id)
        purchase.frontMat = matTypeRepository.findOne(purchase.frontMat!!.id)
        purchase.user = email
        purchase.transactionStatus = Payment.Status.pending.name
        purchase.status = PurchaseStatus.PENDING
        purchase.stampDatetime = Instant.now().toEpochMilli()

        try {
            validatePurchasePrice(purchase)
        } catch(e: IllegalPurchaseException) {
            return ResponseEntity.badRequest().build()
        }

        val preference = Preference()
        val item = Item()
        item.id = UUID.randomUUID().toString()
                item.title = "Marco personalizado"
                item.quantity = 1
                item.currencyId = "ARS"
                item.unitPrice = roundTwoDecimals(purchase.backboardPrice!! + purchase.framePrice!! + purchase.frontMatPrice!! + purchase.frameGlassPrice!!)
        val payer = Payer()
        payer.email = email
        preference.payer = payer
        preference.appendItem(item)
        purchase.transactionId = UUID.randomUUID().toString()
        preference.externalReference = purchase.transactionId
        val backUrls = BackUrls(
                "$backUrlBase/purchase/purchase-success",
                "$backUrlBase/purchase/purchase-pending",
                "$backUrlBase/purchase/purchase-failure")
        preference.backUrls = backUrls
        val savedPreference = preference.save()
        purchase.transactionInitialPoint = savedPreference.initPoint
        val savedPurchase = repo.save(purchase)
        return ResponseEntity.ok(savedPurchase)
    }

    fun validatePurchasePrice(purchase: Purchase) {
        if(purchase.frame!!.price != purchase.framePrice
            || purchase.backboardPrice!!.roundToInt() != calculatedPrice(purchase.frame!!, purchase.backboard!!.m2Price!!)
            || purchase.frontMatPrice!!.roundToInt() != calculatedPrice(purchase.frame!!, purchase.frontMat!!.m2Price!!)
            || purchase.frameGlassPrice!!.roundToInt() != calculatedPrice(purchase.frame!!, purchase.frameGlass!!.m2Price!!)) {
            throw IllegalPurchaseException("The purchase price does not match the calculated price.")
        }
    }

    fun calculatedPrice(frame : Frame, m2Price : Float): Int {
        return (m2Price * (frame.height!! / 100) * (frame.length!! / 100)).roundToInt()
    }

    fun roundTwoDecimals(d: Float): Float {
        val twoDForm = DecimalFormat("#.##")
        return java.lang.Float.valueOf(twoDForm.format(d))
    }

    @RequestMapping(value = [""], method = [RequestMethod.GET])
    fun getAll(
            @RequestParam(value = "page") page: Int,
            @RequestParam("size") size: Int,
            principal: Principal) : ResponseEntity<Resources<Purchase>> {
        val email = JWT.decode((principal as AuthenticationJsonWebToken).token).getClaim("https://email").asString()
        val pagedResponse = repo.findByUser(email, PageRequest(page, size, Sort(Sort.Direction.DESC, "lastModifiedDate")))
        pagedResponse.content.forEach { purchase ->
            purchase.frame!!.picture!!.url = pictureService.generatePictureUrl(purchase.frame!!.picture!!.key!!, true)
            purchase.backboard!!.picture!!.url = pictureService.generatePictureUrl(purchase.backboard!!.picture!!.key!!, true)
            purchase.frontMat!!.picture!!.url = pictureService.generatePictureUrl(purchase.frontMat!!.picture!!.key!!, true)
            if(purchase.userPicture != null) {
                purchase.userPicture!!.picture!!.url = pictureService.generatePictureUrl(purchase.userPicture!!.picture!!.key!!, true)
            }
        }
        val purchasesPage = PagedResources.PageMetadata(
                pagedResponse.size.toLong(),
                pagedResponse.number.toLong(),
                pagedResponse.totalElements,
                pagedResponse.totalPages.toLong())
        val resources = PagedResources(pagedResponse.content, purchasesPage)
        return ResponseEntity.ok(resources)
    }

    @RequestMapping(value = ["/admin"], method = [RequestMethod.GET])
    fun getAllByStatus(
            @RequestParam(value = "status", required = false) status: PurchaseStatus?,
            @RequestParam(value = "page") page: Int,
            @RequestParam("size") size: Int) : ResponseEntity<Resources<Purchase>> {
        val pageRequest = PageRequest(page, size, Sort(Sort.Direction.DESC, "lastModifiedDate"))
        val pagedResponse = if(status != null) {
            repo.findByStatus(status, pageRequest)
        } else {
            repo.findAll(pageRequest)
        }
        pagedResponse.content.forEach { purchase ->
            purchase.frame!!.picture!!.url = pictureService.generatePictureUrl(purchase.frame!!.picture!!.key!!, true)
            purchase.backboard!!.picture!!.url = pictureService.generatePictureUrl(purchase.backboard!!.picture!!.key!!, true)
            purchase.frontMat!!.picture!!.url = pictureService.generatePictureUrl(purchase.frontMat!!.picture!!.key!!, true)
            purchase.userPicture?.picture?.url = pictureService.generatePictureUrl(purchase.userPicture!!.picture!!.key!!, true)
        }

        val userPicturesPage = PagedResources.PageMetadata(
                pagedResponse.size.toLong(),
                pagedResponse.number.toLong(),
                pagedResponse.totalElements,
                pagedResponse.totalPages.toLong())
        val resources = PagedResources(pagedResponse.content, userPicturesPage)
        return ResponseEntity.ok(resources)
    }

    @RequestMapping(value = ["/admin/fulfil/{id}"], method = [RequestMethod.PATCH])
    fun fulfillPurchase(@PathVariable(value = "id") id: Long) : ResponseEntity<Purchase>{
        val purchase = repo.findOne(id)
        purchase.transactionStatus = "FULFILLED"
        return ResponseEntity.ok(repo.save(purchase))
    }

}

interface PurchaseRepository : PagingAndSortingRepository<Purchase, Long> {
    fun findByUser(user: String, pageable: Pageable): Page<Purchase>
    fun findByStatus(status: PurchaseStatus, pageable: Pageable): Page<Purchase>
    fun findByUserPicture(userPicture: UserPicture): List<Purchase>
}

class IllegalPurchaseException(message: String): Exception(message)