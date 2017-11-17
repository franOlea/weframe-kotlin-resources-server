package org.weframe.kotlinresourcesserver.backmat

import org.weframe.kotlinresourcesserver.mattype.MatType
import javax.persistence.*

/**
 * Represents a back mat of a personalized picture frame.
 */
@Entity
@Table(name = "BACK_MATS")
class BackMat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "MAT_TYPE", nullable = false)
    var matType: MatType? = null

    @Column(name = "HORIZONTAL_BEZEL", nullable = false)
    var horizontalBezel: Float? = null

    @Column(name = "VERTICAL_BEZEL", nullable = false)
    var verticalBezel: Float? = null

    /**
     * DO NOT USE, needed for JPA only.
     */
    constructor()

    constructor(matType: MatType?, horizontalBezel: Float?, verticalBezel: Float?) {
        this.matType = matType
        this.horizontalBezel = horizontalBezel
        this.verticalBezel = verticalBezel
    }


    override fun toString(): String {
        return "BackMat(id=$id, matType=$matType, outerHorizontalBezel=$horizontalBezel, outerVerticalBezel=$verticalBezel)"
    }


}