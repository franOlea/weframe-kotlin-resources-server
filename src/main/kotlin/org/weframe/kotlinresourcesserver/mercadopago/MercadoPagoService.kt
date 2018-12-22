package org.weframe.kotlinresourcesserver.mercadopago

import com.mercadopago.resources.Payment
import org.springframework.data.domain.PageRequest
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.weframe.kotlinresourcesserver.purchase.PurchaseRepository
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Component
class MercadoPagoPurchaseChecker(private val mercadoPagoCredentials: MercadoPagoCredentials,
                                 private val purchaseRepository: PurchaseRepository,
                                 private val transactionRepository: TransactionRepository) {

    @Scheduled(fixedRate = 10_000)
    fun validatePaymentPendindPurchases() {

        val openPurchases = purchaseRepository.findByStatus("OPEN", PageRequest(0, 100))
        openPurchases.forEach {
            val filters = HashMap<String, String>()
            filters["external_reference"] = it.transactionId!!
            val payments = Payment.search(filters, false)
            payments.resources().forEach {
                println(it)
            }
        }
    }

}

@Entity
@Table(name = "TRANSACTIONS")
open class Transaction {
    @Id
    @Column(name = "ID", nullable = false)
    var id: Long? = null

    @Column(name = "TOPIC", nullable = false)
    var topic: String? = null
}

interface TransactionRepository : PagingAndSortingRepository<Transaction, Long>