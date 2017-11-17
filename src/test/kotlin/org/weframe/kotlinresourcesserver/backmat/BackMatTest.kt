package org.weframe.kotlinresourcesserver.backmat

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.Mockito.mock
import org.weframe.kotlinresourcesserver.mattype.MatType
import org.junit.Test as test
import org.junit.Before as before

class BackMatTest {

    private val horizontalBezel = 1.0f
    private val verticalBezel = 1.0f

    private var matType: MatType? = null

    @before fun setUp() {
        matType = mock(MatType::class.java)
    }

    @test fun create() {
        val backMat: BackMat = BackMat(matType, horizontalBezel, verticalBezel)

        assertThat(backMat.matType, `is`(matType))
        assertThat(backMat.horizontalBezel, `is`(horizontalBezel))
        assertThat(backMat.verticalBezel, `is`(verticalBezel))
        assertThat(backMat.toString(), equalTo(BackMat(matType, horizontalBezel, verticalBezel).toString()))
    }

    @test fun setters() {
        val backMat: BackMat = BackMat()
        backMat.matType = matType
        backMat.horizontalBezel = horizontalBezel
        backMat.verticalBezel = verticalBezel

        assertThat(backMat.matType, `is`(matType))
        assertThat(backMat.horizontalBezel, `is`(horizontalBezel))
        assertThat(backMat.verticalBezel, `is`(verticalBezel))
        assertThat(backMat.toString(), equalTo(BackMat(matType, horizontalBezel, verticalBezel).toString()))
    }
}