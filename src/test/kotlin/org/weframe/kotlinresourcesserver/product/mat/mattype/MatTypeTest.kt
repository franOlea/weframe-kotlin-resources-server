package org.weframe.kotlinresourcesserver.product.mat.mattype

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test as test
import org.junit.Before as before
import org.mockito.Mockito.mock
import org.weframe.kotlinresourcesserver.product.picture.Picture

class MatTypeTest {

    private val name = "name"
    private val description = "description"
    private val m2Price = 1.0f

    private var picture: Picture? = null

    @before fun setUp() {
        picture = mock(Picture::class.java)
    }

    @test fun create() {
        val matType = MatType(name, description, picture!!, m2Price)
        assertThat(matType.name, `is`(name))
        assertThat(matType.description, `is`(description))
        assertThat(matType.picture, `is`(picture))
        assertThat(matType.m2Price, `is`(m2Price))
        assertThat(matType.toString(), equalTo(MatType(name, description, picture!!, m2Price).toString()))
    }

    @test fun setters() {
        val matType = MatType()
        matType.name = name
        matType.description = description
        matType.picture = picture
        matType.m2Price = m2Price

        assertThat(matType.name, `is`(name))
        assertThat(matType.description, `is`(description))
        assertThat(matType.picture, `is`(picture))
        assertThat(matType.m2Price, `is`(m2Price))
        assertThat(matType.toString(), equalTo(MatType(name, description, picture!!, m2Price).toString()))
    }

}