@file:Suppress("unused")

package org.weframe.kotlinresourcesserver.purchase

import java.math.BigDecimal
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

    @OneToMany(fetch = FetchType.EAGER, cascade =  arrayOf(CascadeType.ALL), mappedBy = "purchase")
    var items: MutableSet<Item>? = null

    @Column(name = "STAMP_DATETIME", nullable = false)
    var stampDatetime: Long? = null

    override fun toString(): String {
        return "Purchase(id=$id, user=$user, items=$items, stampDatetime=$stampDatetime)"
    }

}

@Entity
@Table(name = "PURCHASE_ITEMS")
open class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    var id: Long? = null

    @Column(name = "NAME", nullable = false)
    var name: String? = null

    @Column(name = "QUANTITY", nullable = false)
    var quantity: Int? = null

    @Column(name = "UNIT_PRICE", precision = 8, scale = 2)
    var unitPrice: BigDecimal? = null

    @OneToOne(fetch = FetchType.EAGER, cascade =  arrayOf(CascadeType.ALL), orphanRemoval = true)
    @JoinColumn(name = "purchase", nullable = false)
    var purchase: Purchase? = null

}