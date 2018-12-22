@file:Suppress("unused")

package org.weframe.kotlinresourcesserver.purchase

import org.weframe.kotlinresourcesserver.product.backboard.Backboard
import org.weframe.kotlinresourcesserver.product.frame.Frame
import org.weframe.kotlinresourcesserver.product.mat.mattype.MatType
import org.weframe.kotlinresourcesserver.product.picture.user.UserPicture
import javax.persistence.*

@Entity
@Table(name = "PURCHASES")
open class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Long? = null

    @Column(name = "USER", nullable = false)
    var user: String? = null

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "USER_PICTURE", nullable = false)
    var userPicture: UserPicture? = null

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "FRAME", nullable = false)
    var frame: Frame? = null

    @Column(name = "FRAME_PRICE", nullable = false)
    var framePrice: Float? = null

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "BACKBOARD", nullable = false)
    var backboard: Backboard? = null

    @Column(name = "BACKBOARD_PRICE", nullable = false)
    var backboardPrice: Float? = null

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "FRONT_MAT", nullable = false)
    var frontMat: MatType? = null

    @Column(name = "FRONT_MAT_PRICE", nullable = false)
    var frontMatPrice: Float? = null

    @Column(name = "STAMP_DATETIME", nullable = false)
    var stampDatetime: Long? = null

    @Column(name = "STATUS", nullable = false)
    var status: String? = null

    @Column(name = "TRANSACTION_ID", nullable = false)
    var transactionId: String? = null

    override fun toString(): String {
        return "Purchase(id=$id, user=$user, userPicture=$userPicture, frame=$frame, framePrice=$framePrice, " +
                "backboard=$backboard, backboardPrice=$backboardPrice, frontMat=$frontMat, " +
                "frontMatPrice=$frontMatPrice, stampDatetime=$stampDatetime)"
    }


}