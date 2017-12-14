package org.weframe.kotlinresourcesserver.product.frameglass

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.weframe.kotlinresourcesserver.product.frameglass.FrameGlass
import org.junit.Test as test
import org.junit.Before as before

class FrameGlassTest {

    private val name = "name"
    private val description = "description"
    private val m2Price = 1.0f

    @test fun create() {
        val frameGlass = FrameGlass(name, description, m2Price)
        assertThat(frameGlass.name, `is`(name))
        assertThat(frameGlass.description, `is`(description))
        assertThat(frameGlass.m2Price, `is`(m2Price))
        assertThat(frameGlass.toString(), equalTo(FrameGlass(name, description, m2Price).toString()))
    }

    @test fun setters() {
        val frameGlass = FrameGlass()
        frameGlass.name = name
        frameGlass.description = description
        frameGlass.m2Price = m2Price

        assertThat(frameGlass.name, `is`(name))
        assertThat(frameGlass.description, `is`(description))
        assertThat(frameGlass.m2Price, `is`(m2Price))
        assertThat(frameGlass.toString(), equalTo(FrameGlass(name, description, m2Price).toString()))
    }

}