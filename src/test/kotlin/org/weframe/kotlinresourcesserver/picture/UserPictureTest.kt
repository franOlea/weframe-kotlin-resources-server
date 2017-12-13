package org.weframe.kotlinresourcesserver.picture

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before as before
import org.junit.Test as test
import org.mockito.Mockito

class UserPictureTest {

    private val id: Long = 0
    private val user: String = "user"
    private var picture: Picture? = null

    @before fun setUp() {
        picture = Mockito.mock(Picture::class.java)
    }

    @test fun create() {
        val userPicture = UserPicture(picture!!, user)
        assertThat(userPicture.picture, `is`(picture))
        assertThat(userPicture.user, `is`(user))
        assertThat(userPicture.toString(), equalTo(UserPicture(picture!!, user).toString()))
    }

    @test fun setters() {
        val userPicture = UserPicture()
        userPicture.id = id
        userPicture.picture = picture
        userPicture.user = user
        assertThat(userPicture.id, `is`(id))
        assertThat(userPicture.picture, `is`(picture))
        assertThat(userPicture.user, `is`(user))
        assertThat(userPicture.toString(), equalTo(UserPicture(picture!!, user).toString()))
    }

}