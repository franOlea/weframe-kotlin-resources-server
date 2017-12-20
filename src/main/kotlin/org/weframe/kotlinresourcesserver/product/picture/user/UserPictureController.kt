package org.weframe.kotlinresourcesserver.product.picture.user

import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.weframe.kotlinresourcesserver.product.picture.Picture
import java.security.Principal

@RestController
@RequestMapping("/user-pictures")
class UserPictureController(val repository: UserPictureRepository) {

    @RequestMapping(value = "", method = arrayOf(RequestMethod.POST))
    fun create(@RequestBody picture: Picture, principal: Principal): ResponseEntity<*> {
        repository.save(UserPicture(picture, principal.name))
        return ResponseEntity.ok("")
    }

    @RequestMapping(value = "", method = arrayOf(RequestMethod.GET))
    fun getAll(@RequestParam(value = "page") page: Int, @RequestParam("size") size: Int, principal: Principal)
            : ResponseEntity<List<UserPicture>> {
        return ResponseEntity.ok(repository.findByUser(principal.name, PageRequest(page, size)))
    }

    @RequestMapping(value = "/count", method = arrayOf(RequestMethod.GET))
    fun count(principal: Principal): ResponseEntity<Long> {
        return ResponseEntity.ok(repository.countByUser(principal.name))
    }

}