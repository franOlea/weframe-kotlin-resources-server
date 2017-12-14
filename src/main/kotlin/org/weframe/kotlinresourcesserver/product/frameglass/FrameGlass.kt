package org.weframe.kotlinresourcesserver.product.frameglass

import org.weframe.kotlinresourcesserver.product.Product
import javax.persistence.*


/**
 * Represents the frame glass.
 */
@Entity
@Table(name = "FRAME_GLASSES")
open class FrameGlass : Product {

    @Column(name= "M2_PRICE", nullable = false)
    var m2Price: Float? = null

    /**
     * DO NOT USE, needed for JPA only.
     */
    constructor()

    constructor(name: String, description: String, m2Price: Float)
            : super(name, description) {
        this.m2Price = m2Price
    }

    override fun toString(): String {
        return "FrameGlass(id=$id, name=$name, m2Price=$m2Price)"
    }
}