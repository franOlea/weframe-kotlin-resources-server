package org.weframe.kotlinresourcesserver.product.frame

import org.springframework.data.rest.core.annotation.RestResource
import org.weframe.kotlinresourcesserver.product.Product
import org.weframe.kotlinresourcesserver.product.picture.Picture
import javax.persistence.*


/**
 * Represents the frame of the products.
 */
@Entity
@Table(name = "FRAMES")
open class Frame : Product {

    @Column(name= "HEIGHT", nullable = false)
    var height: Float? = null

    @Column(name= "LENGTH", nullable = false)
    var length: Float? = null

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = arrayOf(CascadeType.ALL))
    @JoinColumn(name = "PICTURE")
    @RestResource(exported=false)
    var picture: Picture? = null

    @Column(name= "M2_PRICE")
    var price: Float? = null

    constructor()

    constructor(name: String, description: String, height: Float, length: Float, picture: Picture, price: Float) : super(name, description) {
        this.height = height
        this.length = length
        this.picture = picture
        this.price = price
    }

    override fun toString(): String {
        return "Frame(id=$id, name=$name, height=$height, length=$length, picture=$picture, price=$price)"
    }


}