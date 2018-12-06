package org.weframe.kotlinresourcesserver.product.mat.mattype

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
@RepositoryEventHandler(MatType::class)
class MatTypeEventHandler(private val pictureRepository: PictureRepository) {

    @Suppress("unused")
    @HandleBeforeSave
    @HandleBeforeCreate
    fun beforeSave(matType: MatType) {
        val picture = pictureRepository.findByKey(matType.picture!!.key!!)
        matType.picture = picture
    }

}

@RepositoryRestController
class MatTypeController(private val repository: MatTypeRepository,
                      private val pictureService: PictureFileService) {

    @RequestMapping(method = [RequestMethod.GET], value = ["/mat-types"])
    @ResponseBody
    fun getMatTypes(@RequestParam(value="page", defaultValue="0") page: Int,
                  @RequestParam(value="size", defaultValue="10") size: Int): ResponseEntity<Resources<MatType>>? {
        val matTypes = repository.findAll(PageRequest(page, size))
        matTypes.content.forEach {
            matType -> matType.picture!!.url = pictureService.generatePictureUrl(matType.picture!!.key!!, true)
        }
        val matTypesPage = PagedResources.PageMetadata(
                matTypes.size.toLong(),
                matTypes.number.toLong(),
                matTypes.totalElements,
                matTypes.totalPages.toLong())
        val resources = PagedResources(matTypes.content, matTypesPage)
        return ResponseEntity.ok(resources)
    }

    @RequestMapping(method = [RequestMethod.GET], value = ["/mat-types/{id}"])
    @ResponseBody
    fun getMatType(@PathVariable("id") id: Long): ResponseEntity<Resource<MatType>> {
        val matType = repository.findOne(id)
        matType.picture!!.url = pictureService.generatePictureUrl(matType.picture!!.key!!, false)
        val resource = Resource(matType)
        return ResponseEntity.ok(resource)
    }

}