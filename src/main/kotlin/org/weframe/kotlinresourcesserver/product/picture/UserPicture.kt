package org.weframe.kotlinresourcesserver.product.picture

import javax.persistence.*

@Entity
@Table(name = "USER_PICTURES")
open class UserPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Long? = null

    @OneToOne(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL), orphanRemoval = true)
    @JoinColumn(name = "PICTURE", nullable = false)
    var picture: Picture? = null

    @Column(name = "USER", nullable = false)
    var user: String? = null

    /**
     * DO NOT USE, needed for JPA only.
     */
    constructor()

    constructor(picture: Picture,
                user: String) {
        this.picture = picture
        this.user = user
    }

    override fun toString(): String {
        return "UserPicture(id=$id, picture=${picture!!.key}, user=$user)"
    }

}