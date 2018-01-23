package org.weframe.kotlinresourcesserver.product.sale

import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.weframe.kotlinresourcesserver.product.FramedPicture
import java.util.*
import javax.persistence.*

@SuppressWarnings("unused")
@Entity
@Table(name = "SALES")
open class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Long? = null

    @CreatedBy
    @Column(name = "USER", nullable = false)
    var user: String? = null

    @CreatedDate
    @Column(name = "CREATED_DATE", nullable = false)
    var date: Date? = null

    @ManyToMany
    @JoinTable(
            name="SALES_FRAMED_PICTURES",
            joinColumns= arrayOf(JoinColumn(name="SALE_ID", referencedColumnName="ID")),
            inverseJoinColumns= arrayOf(JoinColumn(name="FRAMED_PICTURE_ID", referencedColumnName="ID")))
    var framedPictures: MutableList<FramedPicture>? = null

}