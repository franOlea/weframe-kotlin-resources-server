package org.weframe.kotlinresourcesserver.product

import org.springframework.data.annotation.CreatedBy
import org.weframe.kotlinresourcesserver.product.backboard.Backboard
import org.weframe.kotlinresourcesserver.product.frame.Frame
import org.weframe.kotlinresourcesserver.product.frameglass.FrameGlass
import org.weframe.kotlinresourcesserver.product.mat.Mat
import org.weframe.kotlinresourcesserver.product.picture.LocatedPicture
import javax.persistence.*


@SuppressWarnings("unused")
@Entity
@Table(name = "PICTURE_FRAMES")
open class FramedPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name = "BACK_BOARD", nullable = false)
    var backBoard: Backboard? = null

    @OneToOne(fetch = FetchType.EAGER, cascade =  arrayOf(CascadeType.ALL), orphanRemoval = true)
    @JoinColumn(name = "BACK_MAT", nullable = false)
    var backMat: Mat? = null

    @OneToMany(fetch = FetchType.EAGER, cascade =  arrayOf(CascadeType.ALL), mappedBy = "framedPicture")
    var locatedPictures: MutableSet<LocatedPicture>? = null

    @OneToOne(fetch = FetchType.EAGER, cascade =  arrayOf(CascadeType.ALL), orphanRemoval = true)
    @JoinColumn(name = "WINDOW_MAT", nullable = false)
    var windowMat: Mat? = null

    @ManyToOne
    @JoinColumn(name = "FRAME_GLASS", nullable = false)
    var frameGlass: FrameGlass? = null

    @ManyToOne
    @JoinColumn(name = "FRAME", nullable = false)
    var frame: Frame? = null

    @CreatedBy
    @Column(name = "USER", nullable = false)
    var user: String? = null

    /**
     * DO NOT USE, needed for JPA only.
     */
    constructor()

    constructor(backBoard: Backboard, backMat: Mat, locatedPictures: MutableSet<LocatedPicture>, windowMat: Mat, frameGlass: FrameGlass, frame: Frame) {
        this.backBoard = backBoard
        this.backMat = backMat
        this.locatedPictures = locatedPictures
        this.windowMat = windowMat
        this.frameGlass = frameGlass
        this.frame = frame
    }

    override fun toString(): String {
        return "FramedPicture(id=$id, backBoard=$backBoard, backMat=$backMat, locatedPictures=$locatedPictures, windowMat=$windowMat, frameGlass=$frameGlass, frame=$frame)"
    }

}