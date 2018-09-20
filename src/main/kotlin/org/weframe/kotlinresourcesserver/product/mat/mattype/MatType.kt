package org.weframe.kotlinresourcesserver.product.mat.mattype

import org.springframework.data.rest.core.annotation.RestResource
import org.weframe.kotlinresourcesserver.product.Product
import org.weframe.kotlinresourcesserver.product.picture.Picture
import javax.persistence.*

/**
 * Represents the matting types of the products.
 */
@Entity
@Table(name = "MAT_TYPES")
open class MatType : Product {

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
        return "MatType(id=$id, name=$name, picture=$picture, m2Price=$m2Price)"
    }

}