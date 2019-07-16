package org.weframe.kotlinresourcesserver.product.frameglass

import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@RepositoryRestController
class FrameGlassController(private val repository: FrameGlassRepository) {

    @RequestMapping(method = [RequestMethod.DELETE], value = ["/frame-glasses/{id}"])
    @ResponseBody
    fun deleteBackboard(@PathVariable("id") id: Long): ResponseEntity<*> {
        val frameGlass = repository.findOne(id)
        frameGlass.deleted = true
        repository.save(frameGlass)
        return ResponseEntity.ok("")
    }
}