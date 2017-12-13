package org.weframe.kotlinresourcesserver.picture

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.weframe.kotlinresourcesserver.picture.file.MultipartFileReader
import org.weframe.kotlinresourcesserver.picture.file.PictureFileService
import java.util.*

@RestController
@RequestMapping("/pictures")
class PictureController(val fileService: PictureFileService, val repository: PictureRepository, val fileReader: MultipartFileReader) {

    @RequestMapping(value = "", method = arrayOf(RequestMethod.POST))
    fun create(@RequestParam(value = "file") multipartFile: MultipartFile,
               @RequestParam(value = "name") name: String,
               @RequestParam(value = "formatName") imageFormatName: String): ResponseEntity<*> {
        val image = fileReader.read(multipartFile)
        val key = UUID.randomUUID().toString()
        fileService.savePicture(image, key, imageFormatName)
        repository.save(Picture(name, key))
        return ResponseEntity.ok("Picture uploaded.")
    }

    @RequestMapping(value = "/{key}", method = arrayOf(RequestMethod.DELETE))
    fun delete(@PathVariable("key") key: String): ResponseEntity<*> {
        fileService.deletePicture(key)
        repository.deleteByKey(key)
        return ResponseEntity.ok("Picture deleted.")
    }

    @RequestMapping(value = "", method = arrayOf(RequestMethod.GET))
    fun getPicture(@RequestParam(name = "key") key: String,
                   @RequestParam(name = "thumbnail", required = false, defaultValue = "true") thumbnail: Boolean): ResponseEntity<*> {
        val picture = repository.findByKey(key)
        picture.url = fileService.generatePictureUrl(key, thumbnail)
        return ResponseEntity.ok(picture)
    }

    @RequestMapping(value = "/url/{key}", method = arrayOf(RequestMethod.GET))
    fun getPictureUrl(@PathVariable("key") key: String,
                      @RequestParam(name = "thumbnail", required = false, defaultValue = "true") thumbnail: Boolean): ResponseEntity<*> {
        return ResponseEntity.ok(fileService.generatePictureUrl(key, thumbnail))
    }

}