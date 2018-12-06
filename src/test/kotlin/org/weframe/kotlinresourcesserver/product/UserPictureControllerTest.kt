package org.weframe.kotlinresourcesserver.product

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.mockito.Mockito.`when` as whenever
import org.weframe.kotlinresourcesserver.product.picture.Picture
import org.weframe.kotlinresourcesserver.product.picture.PictureRepository
import org.weframe.kotlinresourcesserver.product.picture.file.PictureFileService
import org.weframe.kotlinresourcesserver.product.picture.user.UserPicture
import org.weframe.kotlinresourcesserver.product.picture.user.UserPictureController
import org.weframe.kotlinresourcesserver.product.picture.user.UserPictureRepository
import java.security.Principal
import org.junit.Test as test
import org.junit.Before as before

class UserPictureControllerTest {

    var userPicture: UserPicture? = null
    var picture: Picture? = null
    val principalName = "name"
    var principal: Principal? = null
    var userPictureRepository: UserPictureRepository? = null
    var pictureRepository: PictureRepository? = null
    var service: PictureFileService? = null

    @before fun setUp() {
        userPicture = mock(UserPicture::class.java)
        userPictureRepository = mock(UserPictureRepository::class.java)
        principal = mock(Principal::class.java)
        picture = mock(Picture::class.java)
        service = mock(PictureFileService::class.java)
        pictureRepository = mock(PictureRepository::class.java)
    }

    @test fun create() {
        UserPictureController(userPictureRepository!!, pictureRepository!!, service!!)
    }

    @test fun createUserPicture() {
        whenever(principal!!.name).thenReturn(principalName)
        val controller = UserPictureController(userPictureRepository!!, pictureRepository!!, service!!)
        controller.create(picture!!, principal!!)
        verify(userPictureRepository, times(1))!!
                .save(any(UserPicture::class.java))
    }

    @test fun getAll() {
        val userPictures = listOf(userPicture!!)
        PageImpl(userPictures, PageRequest(0, 1), 1L)
        whenever(userPictureRepository!!.findByUser(principalName, PageRequest(1,1)))
                .thenReturn(PageImpl(userPictures, PageRequest(0, 1), 1L))
        whenever(principal!!
                .name).thenReturn(principalName)
        val response = UserPictureController(userPictureRepository!!, pictureRepository!!, service!!).getAll(1,1, principal!!)
        assertThat(response.statusCode, `is`(HttpStatus.OK))
        assertThat(response.body.content.contains(userPicture), `is`(true))
    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

}