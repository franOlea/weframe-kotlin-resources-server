package org.weframe.kotlinresourcesserver.product.backboard

import org.springframework.data.domain.PageRequest
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.HandleBeforeSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.Resource
import org.springframework.hateoas.Resources
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import org.weframe.kotlinresourcesserver.product.picture.PictureRepository
import org.weframe.kotlinresourcesserver.product.picture.file.PictureFileService

@Component
@RepositoryEventHandler(Backboard::class)
class BackboardEventHandler(private val pictureRepository: PictureRepository) {

    @Suppress("unused")
    @HandleBeforeSave
    @HandleBeforeCreate
    fun beforeSave(backboard: Backboard) {
        val picture = pictureRepository.findByKey(backboard.picture!!.key!!)
        backboard.picture = picture
    }

}

@RepositoryRestController
class BackboardController(private val repository: BackboardRepository,
                          private val pictureService: PictureFileService) {

    @RequestMapping(method = [RequestMethod.GET], value = ["/backboards"])
    @ResponseBody fun getBackboards(@RequestParam(value="page", defaultValue="0") page: Int,
                                    @RequestParam(value="size", defaultValue="10") size: Int): ResponseEntity<Resources<Backboard>>? {
        val backboards = repository.findAll(PageRequest(page, size))
        backboards.content.forEach {
            backboard -> backboard.picture!!.url = pictureService.generatePictureUrl(backboard.picture!!.key!!, true)
        }
        val backboardsPage = PagedResources.PageMetadata(
                backboards.size.toLong(),
                backboards.number.toLong(),
                backboards.totalElements,
                backboards.totalPages.toLong())
        val resources = PagedResources(backboards.content, backboardsPage)
        return ResponseEntity.ok(resources)
    }

    @RequestMapping(method = [RequestMethod.GET], value = ["/frames/{id}"])
    @ResponseBody
    fun getBackboard(@PathVariable("id") id: Long): ResponseEntity<Resource<Backboard>> {
        val backboard = repository.findOne(id)
        backboard.picture!!.url = pictureService.generatePictureUrl(backboard.picture!!.key!!, false)
        val resource = Resource(backboard)
        return ResponseEntity.ok(resource)
    }

}