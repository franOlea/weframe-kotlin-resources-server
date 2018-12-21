package org.weframe.kotlinresourcesserver.product.backboard

import org.springframework.data.rest.core.annotation.RestResource
import org.weframe.kotlinresourcesserver.product.Product
import org.weframe.kotlinresourcesserver.product.picture.Picture
import javax.persistence.*

/**
 * Represents a backboard product.
 */
@Entity
@Table(name = "BACKBOARDS")
open class Backboard : Product {

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = [CascadeType.ALL])
    @JoinColumn(name = "PICTURE", nullable = false)
    @RestResource(exported=false)
    var picture: Picture? = null

    @Column(name= "M2_PRICE", nullable = false)
    var m2Price: Float? = null

    /**
     * DO NOT USE, needed for JPA only.
     */
    constructor()

    constructor(name: String, description: String, picture: Picture, m2Price: Float)
            : super(name, description) {
        this.picture = picture
        this.m2Price = m2Price
    }

    override fun toString(): String {
        return "Backboard(id=$id, name=$name, picture=$picture, m2Price=$m2Price)"
    }

}