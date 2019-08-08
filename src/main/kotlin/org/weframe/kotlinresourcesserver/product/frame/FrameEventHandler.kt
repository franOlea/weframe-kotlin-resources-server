package org.weframe.kotlinresourcesserver.product.frame

import org.springframework.data.domain.PageRequest
import org.springframework.data.rest.core.annotation.HandleBeforeCreate
import org.springframework.data.rest.core.annotation.HandleBeforeSave
import org.springframework.data.rest.core.annotation.RepositoryEventHandler
import org.springframework.data.rest.webmvc.RepositoryRestController
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.Resource
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.*
import org.weframe.kotlinresourcesserver.product.picture.PictureRepository
import org.weframe.kotlinresourcesserver.product.picture.file.PictureFileService

@Component
@RepositoryEventHandler(Frame::class)
class FrameEventHandler(private val pictureRepository: PictureRepository) {

    @Suppress("unused")
    @HandleBeforeSave
    @HandleBeforeCreate
    fun beforeSave(frame: Frame) {
        val picture = pictureRepository.findByKey(frame.picture!!.key!!)
        frame.picture = picture
    }
}

@RepositoryRestController
class FrameController(private val repository: FrameRepository,
                          private val pictureService: PictureFileService) {

    @RequestMapping(method = [RequestMethod.GET], value = ["/frames"])
    @ResponseBody
    fun getFrames(@RequestParam(value="page", defaultValue="0") page: Int,
                      @RequestParam(value="size", defaultValue="10") size: Int): ResponseEntity<PagedResources<Frame>> {
        val frames = repository.findByDeleted(false, PageRequest(page, size))
        frames.content.forEach {
            frame -> frame.picture!!.url = pictureService.generatePictureUrl(frame.picture!!.key!!, true)
        }
        val framesPage = PagedResources.PageMetadata(
                frames.size.toLong(),
                frames.number.toLong(),
                frames.totalElements,
                frames.totalPages.toLong())
        val resources = PagedResources(frames.content, framesPage)
        return ResponseEntity.ok(resources)
    }

    @RequestMapping(method = [RequestMethod.GET], value = ["/frames/{id}"])
    @ResponseBody
    fun getFrame(@PathVariable("id") id: Long): ResponseEntity<Resource<Frame>> {
        val frame = repository.findOne(id)
        frame.picture!!.url = pictureService.generatePictureUrl(frame.picture!!.key!!, false)
        val resource = Resource(frame)
        return ResponseEntity.ok(resource)
    }

    @RequestMapping(method = [RequestMethod.DELETE], value = ["/frames/{id}"])
    @ResponseBody
    fun deleteFrame(@PathVariable("id") id: Long): ResponseEntity<*> {
        val frame = repository.findOne(id)
        frame.deleted = true
        repository.save(frame)
        return ResponseEntity.ok("")
    }

}