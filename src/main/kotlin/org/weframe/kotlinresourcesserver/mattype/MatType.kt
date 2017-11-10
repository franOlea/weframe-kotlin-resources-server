package org.weframe.kotlinresourcesserver.mattype

import org.weframe.kotlinresourcesserver.Product
import org.weframe.kotlinresourcesserver.picture.Picture
import javax.persistence.*

/**
 * Represents the matting types of the products.
 */
@Entity
@Table(name = "MAT_TYPES")
open class MatType : Product {

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "PICTURE", nullable = false)
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
        return "MatType(picture=$picture, m2Price=$m2Price)"
    }

}