package org.weframe.kotlinresourcesserver.picture

import javax.persistence.*

@Entity
@Table(name = "PICTURES")
open class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long = 0

    @Column(name = "NAME", nullable = false)
    var name: String? = null

    @Column(name = "KEY", nullable = false, unique = true)
    var key: String? = null

    @Suppress("ConvertSecondaryConstructorToPrimary", "unused")
    constructor(name: String, key: String) {
        this.name = name
        this.key = key
    }

    override fun toString(): String {
        return "Picture(id=$id, name=$name, key=$key)"
    }

}