package org.weframe.kotlinresourcesserver

import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test as test

class ProductTest {

    private val name = "name"
    private val description = "description"
    private val id :Long = 0

    @test fun create() {
        val product: Product = object: Product(name, description){}
        assertThat(product.name, `is`(name))
        assertThat(product.description, `is`(description))
        assertThat(product.toString(), equalTo(object: Product(name, description){}.toString()))
    }

    @test fun setters() {
        val product: Product = object: Product() { }
        product.id = id
        product.name = name
        product.description = description
        assertThat(product.id, `is`(id))
        assertThat(product.name, `is`(name))
        assertThat(product.description, `is`(description))
    }

}