package org.weframe.kotlinresourcesserver.purchase

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.Resources
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/purchases")
class PurchaseController(private val repo: PurchaseRepository) {

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    fun create(@RequestBody purchase: Purchase, principal: Principal): ResponseEntity<Purchase> {
        purchase.user = principal.name
        purchase.status = "OPEN"
        return ResponseEntity.ok(repo.save(purchase))
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

    @RequestMapping(value = ["/admin"], method = [RequestMethod.PATCH])
    fun fulfillPurchase(@RequestParam(value = "id") id: Long) : ResponseEntity<Purchase>{
        val purchase = repo.findOne(id)
        purchase.status = "FULFILLED"
        return ResponseEntity.ok(repo.save(purchase))
    }

}

interface PurchaseRepository : PagingAndSortingRepository<Purchase, Long> {
    fun findByUser(user: String, pageable: Pageable): Page<Purchase>
    fun findByStatus(status: String, pageable: Pageable): Page<Purchase>
}