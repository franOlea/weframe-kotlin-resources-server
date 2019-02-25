package org.weframe.kotlinresourcesserver.purchase

import com.auth0.jwt.JWT
import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken
import com.mercadopago.resources.Payment
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.Resources
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.weframe.kotlinresourcesserver.product.backboard.BackboardRepository
import org.weframe.kotlinresourcesserver.product.frame.FrameRepository
import org.weframe.kotlinresourcesserver.product.mat.mattype.MatTypeRepository
import org.weframe.kotlinresourcesserver.product.picture.user.UserPictureRepository
import java.security.Principal
import java.time.Instant
import com.mercadopago.resources.Preference
import com.mercadopago.resources.datastructures.preference.Item
import com.mercadopago.resources.datastructures.preference.Payer
import java.util.*
import java.text.DecimalFormat
import javax.websocket.server.PathParam
import com.mercadopago.resources.datastructures.preference.BackUrls
import org.weframe.kotlinresourcesserver.product.picture.file.PictureFileService
import javax.servlet.http.HttpServletResponse


@RestController
@RequestMapping("/purchases")
class PurchaseController(private val repo: PurchaseRepository,
                         private val frameRepository: FrameRepository,
                         private val backboardRepository: BackboardRepository,
                         private val userPictureRepository: UserPictureRepository,
                         private val matTypeRepository: MatTypeRepository,
                         private val pictureService: PictureFileService) {

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

        val preference = Preference()
        val item = Item()
        item.id = UUID.randomUUID().toString()
                item.title = "Marco personalizado"
                item.quantity = 1
                item.currencyId = "ARS"
                item.unitPrice = roundTwoDecimals(purchase.backboardPrice!! + purchase.framePrice!! + purchase.frontMatPrice!!)
        val payer = Payer()
        payer.email = email
        preference.payer = payer
        preference.appendItem(item)
        purchase.transactionId = UUID.randomUUID().toString()
        preference.externalReference = purchase.transactionId
        val backUrls = BackUrls(
                "localhost:9000/purchase/purchase-success",
                "localhost:9000/purchase/purchase-pending",
                "localhost:9000/purchase/purchase-failure")
        preference.backUrls = backUrls
        val savedPreference = preference.save()
        purchase.transactionInitialPoint = savedPreference.initPoint
        return ResponseEntity.ok(repo.save(purchase))
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
        val pagedResponse = repo.findByUser(email, PageRequest(page, size))
        pagedResponse.content.forEach { purchase ->
            purchase.frame!!.picture!!.url = pictureService.generatePictureUrl(purchase.frame!!.picture!!.key!!, true)
            purchase.backboard!!.picture!!.url = pictureService.generatePictureUrl(purchase.backboard!!.picture!!.key!!, true)
            purchase.frontMat!!.picture!!.url = pictureService.generatePictureUrl(purchase.frontMat!!.picture!!.key!!, true)
            purchase.userPicture!!.picture!!.url = pictureService.generatePictureUrl(purchase.userPicture!!.picture!!.key!!, true)
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
            @RequestParam(value = "transactionStatus", defaultValue = "PENDING") status: PurchaseStatus,
            @RequestParam(value = "page") page: Int,
            @RequestParam("size") size: Int) : ResponseEntity<Resources<Purchase>> {
        val pagedResponse = repo.findByStatus(status, PageRequest(page, size))
        val userPicturesPage = PagedResources.PageMetadata(
                pagedResponse.size.toLong(),
                pagedResponse.number.toLong(),
                pagedResponse.totalElements,
                pagedResponse.totalPages.toLong())
        val resources = PagedResources(pagedResponse.content, userPicturesPage)
        return ResponseEntity.ok(resources)
    }

    @RequestMapping(value = ["/admin/fulfil/{id}"], method = [RequestMethod.PATCH])
    fun fulfillPurchase(@PathParam(value = "id") id: Long) : ResponseEntity<Purchase>{
        val purchase = repo.findOne(id)
        purchase.transactionStatus = "FULFILLED"
        return ResponseEntity.ok(repo.save(purchase))
    }

}

interface PurchaseRepository : PagingAndSortingRepository<Purchase, Long> {
    fun findByUser(user: String, pageable: Pageable): Page<Purchase>
    fun findByStatus(status: PurchaseStatus, pageable: Pageable): Page<Purchase>
}