package org.weframe.kotlinresourcesserver.picture.file

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.Mockito.contains
import org.mockito.Mockito.mock
import java.awt.image.BufferedImage
import org.junit.Before as before
import org.junit.Test as test

class InMemoryPictureFileServiceTest {

    val ip = "ip"
    val port = "port"
    val key = "key"
    val format = "format"

    var image: BufferedImage? = null

    @before fun setUp() {
        image = mock(BufferedImage::class.java)
    }

    @test fun create() {
        InMemoryPictureFileService(ip, port)
    }

    @test fun saveLoadAndDeletePicture() {
        val service = InMemoryPictureFileService(ip, port)
        service.savePicture(image!!, key, format)
        val storedImage = service.loadPictureByKey(key)
        assertThat(storedImage!!.first, `is`(image))
        assertThat(storedImage.second, `is`(format))
        service.deletePicture(key)
        assertThat(service.loadPictureByKey(key), nullValue())
    }

    @test fun generateUrl() {
        val service = InMemoryPictureFileService(ip, port)
        service.savePicture(image!!, key, format)
        assertThat(service.generatePictureUrl(key, false)!!.contains(key), `is`(true))
        assertThat(service.generatePictureUrl(key, false)!!.contains("-thumbnail"), `is`(false))
        assertThat(service.generatePictureUrl(key, true)!!.contains(key), `is`(true))
        assertThat(service.generatePictureUrl(key, true)!!.contains("-thumbnail"), `is`(true))
        service.deletePicture(key)
        assertThat(service.generatePictureUrl(key, false)!!.contains("error"), `is`(true))
    }

}