package org.weframe.kotlinresourcesserver.backboard

import org.weframe.kotlinresourcesserver.Product
import org.weframe.kotlinresourcesserver.picture.Picture
import javax.persistence.*

@Entity
@Table(name = "BACK_BOARDS")
class Backboard : Product {

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = arrayOf(CascadeType.ALL),
            orphanRemoval = true)
    @JoinColumn(name = "PICTURE", nullable = false)
    var picture: Picture? = null

    @Column(name= "M2_PRICE", nullable = false)
    var m2Price: Float? = null

    @Suppress("ConvertSecondaryConstructorToPrimary", "unused")
    constructor(name: String, description: String, picture: Picture?, m2Price: Float?)
            : super(name, description) {
        this.picture = picture
        this.m2Price = m2Price
    }

    override fun toString(): String {
        return "Backboard(id=$id, name=$name, picture=${picture?.id}, m2Price=$m2Price)"
    }

}