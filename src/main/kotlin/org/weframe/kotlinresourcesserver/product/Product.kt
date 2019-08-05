package org.weframe.kotlinresourcesserver.product

import org.hibernate.annotations.ColumnDefault
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

    @Column(name = "name", nullable = false, unique = false)
    var name: String? = null

    @Column(name = "description", nullable = false, unique = false)
    var description: String? = null

    @Column(name = "deleted", nullable = false, unique = false)
    @ColumnDefault(value = "false")
    var deleted: Boolean? = null

    /**
     * DO NOT USE, needed for JPA only.
     */
    constructor()

    constructor(name: String, description: String) {
        this.name = name
        this.description = description
        this.deleted = false;
    }

    override fun toString(): String {
        return "Product(id=$id, name=$name, description=$description)"
    }

}