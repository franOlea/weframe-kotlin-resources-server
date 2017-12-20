package org.weframe.kotlinresourcesserver.product.picture

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.Mockito.*
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.weframe.kotlinresourcesserver.product.FramedPicture
import org.weframe.kotlinresourcesserver.product.FramedPictureController
import org.weframe.kotlinresourcesserver.product.FramedPictureRepository
import org.weframe.kotlinresourcesserver.product.backboard.Backboard
import org.weframe.kotlinresourcesserver.product.frame.Frame
import org.weframe.kotlinresourcesserver.product.frameglass.FrameGlass
import org.weframe.kotlinresourcesserver.product.mat.Mat
import java.security.Principal
import org.junit.Before as before
import org.junit.Test as test
import org.mockito.Mockito.`when` as whenever

class FramedPictureControllerTest {

    val principalName = "name"
    val id = 1L

    var principal: Principal? = null
    var repository: FramedPictureRepository? = null
    var backBoard: Backboard? = null
    var backMat: Mat? = null
    var locatedPictures: MutableSet<LocatedPicture>? = null
    var windowMat: Mat? = null
    var frameGlass: FrameGlass? = null
    var frame: Frame? = null

    @before fun setUp() {
        principal = mock(Principal::class.java)
        repository = mock(FramedPictureRepository::class.java)
        backBoard = mock(Backboard::class.java)
        backMat = mock(Mat::class.java)
        locatedPictures = mock(MutableSet::class.java) as MutableSet<LocatedPicture>
        windowMat = mock(Mat::class.java)
        frameGlass = mock(FrameGlass::class.java)
        frame = mock(Frame::class.java)
    }

    @test fun create() {
        FramedPictureController(repository!!)
    }

    @test fun createFramedPicture() {
        whenever(principal!!.name).thenReturn(principalName)
        val framedPicture = FramedPicture(backBoard!!, backMat!!, locatedPictures!!, windowMat!!, frameGlass!!, frame!!)
        val response = FramedPictureController(repository!!).create(framedPicture, principal!!)
        verify(repository, times(1))!!.save(framedPicture)
        assertThat(framedPicture.user, equalTo(principalName))
        assertThat(response.statusCode, `is`(HttpStatus.OK))
    }


    @test fun delete() {
        whenever(principal!!.name).thenReturn(principalName)
        val response = FramedPictureController(repository!!).delete(id, principal!!)
        verify(repository, times(1))!!.deleteByUserAndId(principalName, id)
        assertThat(response.statusCode, `is`(HttpStatus.OK))
    }

    @test fun getAll() {
        val framedPicture = FramedPicture(backBoard!!, backMat!!, locatedPictures!!, windowMat!!, frameGlass!!, frame!!)
        whenever(principal!!.name).thenReturn(principalName)
        whenever(repository!!.findByUser(principalName, PageRequest(1, 1))).thenReturn(listOf(framedPicture))
        val response = FramedPictureController(repository!!).getAll(1, 1, principal!!)
        assertThat(response.statusCode, `is`(HttpStatus.OK))
        assertThat(response.body, equalTo(listOf(framedPicture)))
    }

    @test fun count() {
        whenever(principal!!.name).thenReturn(principalName)
        whenever(repository!!.countByUser(principalName)).thenReturn(1L)
        val response = FramedPictureController(repository!!).count(principal!!)
        assertThat(response.statusCode, `is`(HttpStatus.OK))
        assertThat(response.body, `is`(1L))

    }

}