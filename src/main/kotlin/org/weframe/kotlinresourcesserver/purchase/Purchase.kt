@file:Suppress("unused")

package org.weframe.kotlinresourcesserver.purchase

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.rest.core.annotation.RestResource
import org.weframe.kotlinresourcesserver.product.backboard.Backboard
import org.weframe.kotlinresourcesserver.product.frame.Frame
import org.weframe.kotlinresourcesserver.product.mat.mattype.MatType
import org.weframe.kotlinresourcesserver.product.picture.user.UserPicture
import javax.persistence.*

@Entity
@Table(name = "PURCHASES")
@EntityListeners(AuditingEntityListener::class)
open class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Long? = null

    @Column(name = "USER", nullable = false)
    var user: String? = null

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "USER_PICTURE", nullable = false)
    @RestResource(exported = false)
    var userPicture: UserPicture? = null

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "FRAME", nullable = false)
    @RestResource(exported = false)
    var frame: Frame? = null

    @Column(name = "FRAME_PRICE", nullable = false)
    var framePrice: Float? = null

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "BACKBOARD", nullable = false)
    @RestResource(exported = false)
    var backboard: Backboard? = null

    @Column(name = "BACKBOARD_PRICE", nullable = false)
    var backboardPrice: Float? = null

    @ManyToOne(cascade = [CascadeType.MERGE])
    @JoinColumn(name = "FRONT_MAT", nullable = false)
    @RestResource(exported = false)
    var frontMat: MatType? = null

    @Column(name = "FRONT_MAT_PRICE", nullable = false)
    var frontMatPrice: Float? = null

    @Column(name = "STAMP_DATETIME", nullable = false)
    var stampDatetime: Long? = null

    @Column(name = "TRANSACTION_STATUS", nullable = false)
    var transactionStatus: String? = null

    @Column(name = "TRANSACTION_ID", nullable = false)
    var transactionId: String? = null

    @Column(name = "PURCHASE_STATUS", nullable = false)
    @Enumerated(EnumType.STRING)
    var status: PurchaseStatus? = null

    @Transient
    var transactionInitialPoint: String? = null

    @Column(name = "STREET_ADDRESS_ONE", nullable = false)
    var streetAddressOne: String? = null

    @Column(name = "STREET_ADDRESS_TWO", nullable = false)
    var streetAddressTwo: String? = null

    @Column(name = "ZIP_CODE", nullable = false)
    var zipCode: String? = null

    @Column(name = "PROVINCE", nullable = false)
    var province: String? = null

    @Column(name = "LOCALITY", nullable = false)
    var locality: String? = null

    @LastModifiedDate
    @CreatedDate
    @Column(name = "LAST_MODIFIED_DATE", nullable = false)
    var lastModifiedDate: Long? = null

    override fun toString(): String {
        return "Purchase(id=$id, user=$user, userPicture=$userPicture, frame=$frame, framePrice=$framePrice, " +
                "backboard=$backboard, backboardPrice=$backboardPrice, frontMat=$frontMat, " +
                "frontMatPrice=$frontMatPrice, stampDatetime=$stampDatetime)"
    }

}

enum class PurchaseStatus {
    PENDING,
    MAKING,
    SHIPPING,
    COMPLETE,
    CANCELLED,
    REJECTED,
    ERROR
}