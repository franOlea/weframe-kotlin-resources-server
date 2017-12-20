package org.weframe.kotlinresourcesserver.product

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.Mockito.mock
import org.weframe.kotlinresourcesserver.product.backboard.Backboard
import org.weframe.kotlinresourcesserver.product.frame.Frame
import org.weframe.kotlinresourcesserver.product.frameglass.FrameGlass
import org.weframe.kotlinresourcesserver.product.mat.Mat
import org.weframe.kotlinresourcesserver.product.picture.LocatedPicture
import org.junit.Test as test
import org.junit.Before as before

class FramedPictureTest {

    var backBoard: Backboard? = null
    var backMat: Mat? = null
    var locatedPictures: MutableSet<LocatedPicture>? = null
    var windowMat: Mat? = null
    var frameGlass: FrameGlass? = null
    var frame: Frame? = null

    @Suppress("UNCHECKED_CAST")
    @before fun setUp() {
        backBoard = mock(Backboard::class.java)
        backMat = mock(Mat::class.java)
        locatedPictures = mock(MutableSet::class.java) as MutableSet<LocatedPicture>
        windowMat = mock(Mat::class.java)
        frameGlass = mock(FrameGlass::class.java)
        frame = mock(Frame::class.java)
    }

    @test fun create() {
        val framedPicture = FramedPicture(backBoard!!, backMat!!, locatedPictures!!, windowMat!!, frameGlass!!, frame!!)
        framedPicture.id = 1L
        framedPicture.user = "user"
        assertThat(framedPicture.id, `is`(1L))
        assertThat(framedPicture.user, `is`("user"))
        assertThat(framedPicture.backBoard, `is`(backBoard))
        assertThat(framedPicture.backMat, `is`(backMat))
        assertThat(framedPicture.locatedPictures, `is`(locatedPictures))
        assertThat(framedPicture.windowMat, `is`(windowMat))
        assertThat(framedPicture.frameGlass, `is`(frameGlass))
        assertThat(framedPicture.frame, `is`(frame))
        val equalString = FramedPicture(
                backBoard!!,
                backMat!!,
                locatedPictures!!,
                windowMat!!,
                frameGlass!!,
                frame!!)
        equalString.id = 1L
        assertThat(framedPicture.toString(),
                equalTo(equalString.toString()))
    }

}