package org.weframe.kotlinresourcesserver.product.picture.user

import org.springframework.data.domain.PageRequest
import org.springframework.hateoas.PagedResources
import org.springframework.hateoas.Resources
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.weframe.kotlinresourcesserver.product.picture.Picture
import org.weframe.kotlinresourcesserver.product.picture.PictureRepository
import org.weframe.kotlinresourcesserver.product.picture.file.PictureFileService
import java.security.Principal

@RestController
@RequestMapping("/user-pictures")
class UserPictureController(private val userPictureRepository: UserPictureRepository,
                            private val pictureRepository: PictureRepository,
                            private val pictureService: PictureFileService) {

    @RequestMapping(method = [RequestMethod.POST], value = [""])
    fun create(@RequestBody picture: Picture, principal: Principal): ResponseEntity<UserPicture> {
        val userPicture = UserPicture(pictureRepository.findByKey(picture.key!!), principal.name)
        return ResponseEntity.ok(userPictureRepository.save(userPicture))
    }

    @RequestMapping(method = [RequestMethod.GET], value = [""])
    fun getAll(@RequestParam(value = "page") page: Int, @RequestParam("size") size: Int, principal: Principal)
            : ResponseEntity<Resources<UserPicture>> {
        val userPictures = userPictureRepository.findByUser(principal.name, PageRequest(page, size))
        userPictures.content.forEach{userPicture ->
            userPicture.picture!!.url = pictureService.generatePictureUrl(userPicture.picture!!.key!!, true)
        }
        val userPicturesPage = PagedResources.PageMetadata(
                userPictures.size.toLong(),
                userPictures.number.toLong(),
                userPictures.totalElements,
                userPictures.totalPages.toLong())
        val resources = PagedResources(userPictures.content, userPicturesPage)
        return ResponseEntity.ok(resources)
    }

    @RequestMapping(method = [RequestMethod.GET], value = ["/{id}"])
    fun count(principal: Principal): ResponseEntity<Long> {
        return ResponseEntity.ok(userPictureRepository.countByUser(principal.name))
    }

}