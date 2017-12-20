package org.weframe.kotlinresourcesserver.product

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.mockito.Mockito.`when` as whenever
import org.weframe.kotlinresourcesserver.product.picture.Picture
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
    var repository: UserPictureRepository? = null

    @before fun setUp() {
        userPicture = mock(UserPicture::class.java)
        repository = mock(UserPictureRepository::class.java)
        principal = mock(Principal::class.java)
        picture = mock(Picture::class.java)
    }

    @test fun create() {
        UserPictureController(repository!!)
    }

    @test fun createUserPicture() {
        whenever(principal!!.name).thenReturn(principalName)
        val controller = UserPictureController(repository!!)
        controller.create(picture!!, principal!!)
        verify(repository, times(1))!!
                .save(any(UserPicture::class.java))
    }

    @test fun getAll() {
        whenever(repository!!.findByUser(principalName, PageRequest(1,1)))
                .thenReturn(listOf(userPicture!!))
        whenever(principal!!
                .name).thenReturn(principalName)
        val response = UserPictureController(repository!!).getAll(1,1, principal!!)
        assertThat(response.statusCode, `is`(HttpStatus.OK))
        assertThat(response.body[0], `is`(userPicture))
    }

    @test fun count() {
        whenever(repository!!.countByUser(principalName))
                .thenReturn(1L)
        whenever(principal!!
                .name).thenReturn(principalName)
        val response = UserPictureController(repository!!).count(principal!!)
        assertThat(response.statusCode, `is`(HttpStatus.OK))
        assertThat(response.body, `is`(1L))
    }

    private fun <T> any(): T {
        Mockito.any<T>()
        return uninitialized()
    }

    @Suppress("UNCHECKED_CAST")
    private fun <T> uninitialized(): T = null as T

}