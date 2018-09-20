package org.weframe.kotlinresourcesserver.product

import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/framed-pictures")
class FramedPictureController(val repository: FramedPictureRepository) {

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    fun create(@RequestBody framedPicture: FramedPicture, principal: Principal): ResponseEntity<String> {
        framedPicture.user = principal.name
        repository.save(framedPicture)
        return ResponseEntity.ok("")
    }

    @RequestMapping(value = ["/{id}"], method = [RequestMethod.DELETE])
    fun delete(@PathParam("id") id: Long, principal: Principal): ResponseEntity<String> {
        repository.deleteByUserAndId(principal.name, id)
        return ResponseEntity.ok("")
    }

    @RequestMapping(value = [""], method = [RequestMethod.GET])
    fun getAll(@RequestParam(value = "page") page: Int, @RequestParam("size") size: Int, principal: Principal)
            : ResponseEntity<List<FramedPicture>> {
        return ResponseEntity.ok(repository.findByUser(principal.name, PageRequest(page, size)))
    }

    @RequestMapping(value = ["/count"], method = [RequestMethod.GET])
    fun count(principal: Principal): ResponseEntity<Long> {
        return ResponseEntity.ok(repository.countByUser(principal.name))
    }

}