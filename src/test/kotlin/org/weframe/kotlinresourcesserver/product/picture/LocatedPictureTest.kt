package org.weframe.kotlinresourcesserver.product.picture

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.Mockito.mock
import org.weframe.kotlinresourcesserver.product.FramedPicture
import org.junit.Test as test
import org.junit.Before as before

class LocatedPictureTest {

    val length = 1f
    val height = 2f
    val xPosition = 3f
    val yPosition = 4f
    val zIndex = 5

    var userPicture: UserPicture? = null
    var framedPicture: FramedPicture? = null

    @before fun setUp() {
        userPicture = mock(UserPicture::class.java)
        framedPicture = mock(FramedPicture::class.java)
    }

    @test fun create() {
        val locatedPicture = LocatedPicture(
                userPicture!!,
                length,
                height,
                xPosition,
                yPosition,
                zIndex,
                framedPicture!!)

        assertThat(locatedPicture.userPicture, `is`(userPicture))
        assertThat(locatedPicture.length, `is`(length))
        assertThat(locatedPicture.height, `is`(height))
        assertThat(locatedPicture.xPosition, `is`(xPosition))
        assertThat(locatedPicture.yPosition, `is`(yPosition))
        assertThat(locatedPicture.zIndex, `is`(zIndex))
        assertThat(locatedPicture.framedPicture, `is`(framedPicture))
        assertThat(locatedPicture.toString(),
                equalTo(LocatedPicture(
                        userPicture!!,
                        length,
                        height,
                        xPosition,
                        yPosition,
                        zIndex,
                        framedPicture!!).toString()))
    }

    @test fun setters() {
        val locatedPicture = LocatedPicture()
        locatedPicture.id = 1L
        locatedPicture.userPicture = userPicture
        locatedPicture.length = length
        locatedPicture.height = height
        locatedPicture.xPosition = xPosition
        locatedPicture.yPosition = yPosition
        locatedPicture.zIndex = zIndex
        locatedPicture.framedPicture = framedPicture
        assertThat(locatedPicture.id, `is`(1L))
        assertThat(locatedPicture.userPicture, `is`(userPicture))
        assertThat(locatedPicture.length, `is`(length))
        assertThat(locatedPicture.height, `is`(height))
        assertThat(locatedPicture.xPosition, `is`(xPosition))
        assertThat(locatedPicture.yPosition, `is`(yPosition))
        assertThat(locatedPicture.zIndex, `is`(zIndex))
        assertThat(locatedPicture.framedPicture, `is`(framedPicture))
    }

}