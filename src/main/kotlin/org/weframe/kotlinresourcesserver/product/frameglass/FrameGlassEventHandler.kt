package org.weframe.kotlinresourcesserver.product.frameglass

import org.springframework.data.domain.PageRequest
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.Resources
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RepositoryRestController
class FrameGlassController(private val repository: FrameGlassRepository) {

    @RequestMapping(method = [RequestMethod.GET], value = ["/frame-glasses"])
    @ResponseBody fun getFrameGlasses(@RequestParam(value="page", defaultValue="0") page: Int,
                                    @RequestParam(value="size", defaultValue="10") size: Int): ResponseEntity<Resources<FrameGlass>>? {
        val frameGlasses = repository.findByDeleted(false, PageRequest(page, size))
        val frameGlassPage = PagedResources.PageMetadata(
                frameGlasses.size.toLong(),
                frameGlasses.number.toLong(),
                frameGlasses.totalElements,
                frameGlasses.totalPages.toLong())
        val resources = PagedResources(frameGlasses.content, frameGlassPage)
        return ResponseEntity.ok(resources)
    }

    @RequestMapping(method = [RequestMethod.DELETE], value = ["/frame-glasses/{id}"])
    @ResponseBody
    fun deleteBackboard(@PathVariable("id") id: Long): ResponseEntity<*> {
        val frameGlass = repository.findOne(id)
        frameGlass.deleted = true
        repository.save(frameGlass)
        return ResponseEntity.ok("")
    }
}