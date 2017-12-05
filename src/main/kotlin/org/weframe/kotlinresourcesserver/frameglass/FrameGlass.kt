package org.weframe.kotlinresourcesserver.frameglass

import org.weframe.kotlinresourcesserver.Product
import javax.persistence.*


/**
 * Represents the frame glass.
 */
@Entity
@Table(name = "FRAME_GLASSES")
class FrameGlass : Product {

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