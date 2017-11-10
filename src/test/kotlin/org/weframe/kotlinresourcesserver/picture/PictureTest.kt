package org.weframe.kotlinresourcesserver.picture

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test as test
import org.junit.Before as before

class PictureTest {

    private val name = "name"
    private val key = "key"
    private val id :Long = 0

    @test fun create() {
        val picture: Picture = Picture(name, key)

        assertThat(picture.name, `is`(name))
        assertThat(picture.key, `is`(key))
        assertThat(picture.toString(), equalTo(Picture(name, key).toString()))
    }

    @test fun setters() {
        val picture: Picture = Picture()
        picture.id = id
        picture.name = name
        picture.key = key

        assertThat(picture.id, `is`(id))
        assertThat(picture.name, `is`(name))
        assertThat(picture.key, `is`(key))
    }

}