package org.weframe.kotlinresourcesserver.purchase

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/purchases")
class PurchaseController(private val repo: PurchaseRepository) {

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    fun create(@RequestBody purchase: Purchase, principal: Principal): ResponseEntity<String> {
        purchase.user = principal.name
        repo.save(purchase)
        return ResponseEntity.ok("")
    }

    @RequestMapping(value = [""], method = arrayOf(RequestMethod.GET))
    fun getAll(
            @RequestParam(value = "page") page: Int,
            @RequestParam("size") size: Int,
            principal: Principal) : ResponseEntity<List<Purchase>> {
        return ResponseEntity.ok(repo.findByUser(principal.name, PageRequest(page, size)))
    }

    @RequestMapping(value = ["/count"], method = arrayOf(RequestMethod.GET))
    fun count(principal: Principal): ResponseEntity<Long> {
        return ResponseEntity.ok(repo.countByUser(principal.name))
    }

}

interface PurchaseRepository : PagingAndSortingRepository<Purchase, Long> {
    fun findByUser(user: String, pageable: Pageable): List<Purchase>
    fun countByUser(user: String): Long
}