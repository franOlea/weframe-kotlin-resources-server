package org.weframe.kotlinresourcesserver.product.picture

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import org.springframework.web.multipart.MultipartFile
import org.weframe.kotlinresourcesserver.product.picture.file.MultipartFileReader
import org.weframe.kotlinresourcesserver.product.picture.file.PictureFileService
import java.awt.image.BufferedImage
import org.junit.Before as before
import org.junit.Test as test

class PictureControllerTest {

    val pictureName = "picture"
    val formatName = "format"

    var fileService: PictureFileService? = null
    var repository: PictureRepository? = null
    var fileReader: MultipartFileReader? = null
    var picture: Picture? = null
    var image: BufferedImage? = null
    var multipartFile: MultipartFile? = null

    @before fun setUp() {
        fileService = mock(PictureFileService::class.java)
        repository = mock(PictureRepository::class.java)
        fileReader = mock(MultipartFileReader::class.java)
        picture = mock(Picture::class.java)
        image = mock(BufferedImage::class.java)
        multipartFile = mock(MultipartFile::class.java)
    }

    @test fun create() {
        PictureController(fileService!!, repository!!, fileReader!!)
    }

    @test fun uploadPicture() {
        val controller = PictureController(fileService!!, repository!!, fileReader!!)
        `when`(fileReader!!.read(multipartFile!!)).thenReturn(image)
        val response = controller.create(multipartFile!!, pictureName, formatName)
        verify(fileService, times(1))!!.savePicture(any(), anyString(), anyString())
        verify(repository, times(1))!!.save(any(Picture::class.java))
        assertThat(response.statusCode, `is`(HttpStatus.OK))
    }

    @test fun deletePicture() {
        val controller = PictureController(fileService!!, repository!!, fileReader!!)
        val response = controller.delete("some-key")
        verify(fileService, times(1))!!.deletePicture(anyString())
        verify(repository, times(1))!!.deleteByKey(anyString())
        assertThat(response.statusCode, `is`(HttpStatus.OK))
    }

    @test fun getPictureByKey() {
        val controller = PictureController(fileService!!, repository!!, fileReader!!)
        `when`(repository!!.findByKey("some-key")).thenReturn(picture)
        `when`(fileService!!.generatePictureUrl("some-key", true)).thenReturn("some-url")
        val response = controller.getPicture("some-key", true)
        assertThat(response.statusCode, `is`(HttpStatus.OK))
        assertThat(response.body as Picture, `is`(picture))
    }

    @test fun generatePictureUrl() {
        val controller = PictureController(fileService!!, repository!!, fileReader!!)
        `when`(fileService!!.generatePictureUrl("some-key", true)).thenReturn("some-url")
        val response = controller.getPictureUrl("some-key", true)
        assertThat(response.statusCode, `is`(HttpStatus.OK))
        assertThat(response.body as String, `is`("some-url"))

    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

}