package org.weframe.kotlinresourcesserver.frame

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test as test
import org.junit.Before as before
import org.mockito.Mockito.mock
import org.weframe.kotlinresourcesserver.picture.Picture

class FrameTest {

    private val name = "name"
    private val description = "description"
    private val price = 1.0f
    private val height = 1.0f
    private val length = 1.0f

    private var picture: Picture? = null

    @before fun setUp() {
        picture = mock(Picture::class.java)
    }

    @test fun create() {
        val matType = Frame(name, description, height, length, picture!!, price)
        assertThat(matType.name, `is`(name))
        assertThat(matType.description, `is`(description))
        assertThat(matType.picture, `is`(picture))
        assertThat(matType.price, `is`(price))
        assertThat(matType.height, `is`(height))
        assertThat(matType.length, `is`(length))
        assertThat(matType.toString(), equalTo(Frame(name, description, height, length, picture!!, price).toString()))
    }

    @test fun setters() {
        val matType = Frame()
        matType.name = name
        matType.description = description
        matType.height = height
        matType.length = length
        matType.picture = picture
        matType.price = price

        assertThat(matType.name, `is`(name))
        assertThat(matType.description, `is`(description))
        assertThat(matType.height, `is`(height))
        assertThat(matType.length, `is`(length))
        assertThat(matType.picture, `is`(picture))
        assertThat(matType.price, `is`(price))
        assertThat(matType.toString(), equalTo(Frame(name, description, height, length, picture!!, price).toString()))
    }

}