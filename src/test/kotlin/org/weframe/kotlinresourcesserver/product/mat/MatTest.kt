package org.weframe.kotlinresourcesserver.product.mat

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.mockito.Mockito.mock
import org.weframe.kotlinresourcesserver.product.mat.mattype.MatType
import org.junit.Test as test
import org.junit.Before as before

class MatTest {

    private val horizontalBezel = 1.0f
    private val verticalBezel = 1.0f

    private var matType: MatType? = null

    @before fun setUp() {
        matType = mock(MatType::class.java)
    }

    @test fun create() {
        val mat: Mat = Mat(matType, horizontalBezel, verticalBezel)

        assertThat(mat.matType, `is`(matType))
        assertThat(mat.horizontalBezel, `is`(horizontalBezel))
        assertThat(mat.verticalBezel, `is`(verticalBezel))
        assertThat(mat.toString(), equalTo(Mat(matType, horizontalBezel, verticalBezel).toString()))
    }

    @test fun setters() {
        val mat: Mat = Mat()
        mat.matType = matType
        mat.horizontalBezel = horizontalBezel
        mat.verticalBezel = verticalBezel

        assertThat(mat.matType, `is`(matType))
        assertThat(mat.horizontalBezel, `is`(horizontalBezel))
        assertThat(mat.verticalBezel, `is`(verticalBezel))
        assertThat(mat.toString(), equalTo(Mat(matType, horizontalBezel, verticalBezel).toString()))
    }
}