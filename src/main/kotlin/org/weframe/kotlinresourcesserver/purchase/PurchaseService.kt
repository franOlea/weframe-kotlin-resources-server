package org.weframe.kotlinresourcesserver.purchase

import com.auth0.jwt.JWT
import com.auth0.spring.security.api.authentication.AuthenticationJsonWebToken
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


@RestController
@RequestMapping("/purchases")
class PurchaseController(private val repo: PurchaseRepository,
                         private val frameRepository: FrameRepository,
                         private val backboardRepository: BackboardRepository,
                         private val userPictureRepository: UserPictureRepository,
                         private val matTypeRepository: MatTypeRepository) {

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    fun create(@RequestBody purchase: Purchase, principal: Principal): String {
        purchase.backboard = backboardRepository.findOne(purchase.backboard!!.id)
        purchase.userPicture = userPictureRepository.findOne(purchase.userPicture!!.id)
        purchase.frame = frameRepository.findOne(purchase.frame!!.id)
        purchase.frontMat = matTypeRepository.findOne(purchase.frontMat!!.id)
        purchase.user = principal.name
        purchase.status = "OPEN"
        purchase.stampDatetime = Instant.now().toEpochMilli()

        val email = JWT.decode((principal as AuthenticationJsonWebToken).token).getClaim("https://email").asString()
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
        preference.save()
        purchase.transactionId = UUID.randomUUID().toString()
        preference.externalReference = purchase.transactionId
        repo.save(purchase)
        return "redirect:" + preference.sandboxInitPoint
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
        val pagedResponse = repo.findByUser(principal.name, PageRequest(page, size))
        val userPicturesPage = PagedResources.PageMetadata(
                pagedResponse.size.toLong(),
                pagedResponse.number.toLong(),
                pagedResponse.totalElements,
                pagedResponse.totalPages.toLong())
        val resources = PagedResources(pagedResponse.content, userPicturesPage)
        return ResponseEntity.ok(resources)
    }

    @RequestMapping(value = ["/admin"], method = [RequestMethod.GET])
    fun getAllByStatus(
            @RequestParam(value = "status", defaultValue = "OPEN") status: String,
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
        purchase.status = "FULFILLED"
        return ResponseEntity.ok(repo.save(purchase))
    }

}

interface PurchaseRepository : PagingAndSortingRepository<Purchase, Long> {
    fun findByUser(user: String, pageable: Pageable): Page<Purchase>
    fun findByStatus(status: String, pageable: Pageable): Page<Purchase>
}