package org.weframe.kotlinresourcesserver.backboard

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test as test
import org.junit.Before as before
import org.mockito.Mockito.mock
import org.weframe.kotlinresourcesserver.picture.Picture

class BackboardTest {

    private val name = "name"
    private val description = "description"
    private val m2Price = 1.0f

    private var picture: Picture? = null

    @before fun setUp() {
        picture = mock(Picture::class.java)
    }

    @test fun create() {
        val backboard = Backboard(name, description, picture!!, m2Price)
        assertThat(backboard.name, `is`(name))
        assertThat(backboard.description, `is`(description))
        assertThat(backboard.picture, `is`(picture))
        assertThat(backboard.m2Price, `is`(m2Price))
        assertThat(backboard.toString(), equalTo(Backboard(name, description, picture!!, m2Price).toString()))
    }

    @test fun setters() {
        val backboard = Backboard()
        backboard.name = name
        backboard.description = description
        backboard.picture = picture
        backboard.m2Price = m2Price

        assertThat(backboard.name, `is`(name))
        assertThat(backboard.description, `is`(description))
        assertThat(backboard.picture, `is`(picture))
        assertThat(backboard.m2Price, `is`(m2Price))
        assertThat(backboard.toString(), equalTo(Backboard(name, description, picture!!, m2Price).toString()))
    }

}