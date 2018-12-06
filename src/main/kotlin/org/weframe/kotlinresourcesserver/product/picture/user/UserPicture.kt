package org.weframe.kotlinresourcesserver.product.picture.user

import org.springframework.data.rest.core.annotation.RestResource
import org.weframe.kotlinresourcesserver.product.picture.Picture
import javax.persistence.*

@Entity
@Table(name = "USER_PICTURES")
open class UserPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long? = null

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = [CascadeType.ALL])
    @JoinColumn(name = "PICTURE", nullable = false)
    @RestResource(exported=false)
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