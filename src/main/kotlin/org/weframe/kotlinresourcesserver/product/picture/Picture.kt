package org.weframe.kotlinresourcesserver.product.picture

import com.fasterxml.jackson.annotation.JsonInclude
import javax.persistence.*

/**
 * Represents a picture file reference with a unique key identifier.
 */
@Entity
@Table(name = "PICTURES")
open class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long? = null

    @Column(name = "NAME", nullable = false)
    var name: String? = null

    @Column(name = "KEY", nullable = false, unique = true)
    var key: String? = null

    @Transient
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    var url: String? = null

    /**
     * DO NOT USE, needed for JPA only.
     */
    constructor()

    constructor(name: String, key: String) {
        this.name = name
        this.key = key
    }

    override fun toString(): String {
        return "Picture(id=$id, name=$name, key=$key)"
    }

}