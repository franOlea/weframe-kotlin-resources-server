package org.weframe.kotlinresourcesserver

import javax.persistence.*

/**
 * Represents the abstract product.
 */
@MappedSuperclass
abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long = 0

    @Column(name = "name", nullable = false, unique = true)
    var name: String? = null

    @Column(name = "description", nullable = false, unique = true)
    var description: String? = null

    /**
     * DO NOT USE, needed for JPA only.
     */
    constructor()

    @Suppress("ConvertSecondaryConstructorToPrimary", "unused")
    constructor(name: String, description: String) {
        this.name = name
        this.description = description
    }

    override fun toString(): String {
        return "Product(id=$id, name=$name, description=$description)"
    }

}