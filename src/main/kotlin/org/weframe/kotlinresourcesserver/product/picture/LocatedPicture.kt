package org.weframe.kotlinresourcesserver.product.picture

import org.weframe.kotlinresourcesserver.product.FramedPicture
import org.weframe.kotlinresourcesserver.product.picture.user.UserPicture
import javax.persistence.*


@Entity
@Table(name = "LOCATED_PICTURES")
open class LocatedPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "USER_PICTURE", nullable = false)
    var userPicture: UserPicture? = null

    @Column(name = "LENGTH", nullable = false)
    var length: Float? = null

    @Column(name = "HEIGHT", nullable = false)
    var height: Float? = null

    @Column(name = "X_POSITION", nullable = false)
    var xPosition: Float? = null

    @Column(name = "Y_POSITION", nullable = false)
    var yPosition: Float? = null

    @Column(name = "Z_INDEX", nullable = false)
    var zIndex: Int? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FRAMED_PICTURE", nullable = false)
    var framedPicture: FramedPicture? = null

    /**
     * DO NOT USE, needed for JPA only.
     */
    constructor()

    constructor(userPicture: UserPicture, length: Float, height: Float, xPosition: Float, yPosition: Float, zIndex: Int, framedPicture: FramedPicture?) {
        this.userPicture = userPicture
        this.length = length
        this.height = height
        this.xPosition = xPosition
        this.yPosition = yPosition
        this.zIndex = zIndex
        this.framedPicture = framedPicture
    }

    override fun toString(): String {
        return "LocatedPicture(id=$id, userPicture=$userPicture, length=$length, height=$height, xPosition=$xPosition, yPosition=$yPosition, zIndex=$zIndex, pictureFrame=$framedPicture)"
    }


}