package org.weframe.kotlinresourcesserver.mercadopago

import com.mercadopago.MercadoPago
import com.mercadopago.resources.Payment
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Profile
import org.springframework.data.domain.PageRequest
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.weframe.kotlinresourcesserver.purchase.PurchaseRepository
import org.weframe.kotlinresourcesserver.purchase.PurchaseStatus

@Component
class MercadoPagoPurchaseChecker(private val purchaseRepository: PurchaseRepository,
                                 private val mercadoPagoApi: MercadoPagoAPI) {

    private val log = LoggerFactory.getLogger(MercadoPagoPurchaseChecker::class.java)

    @Scheduled(fixedRate = 10_000)
    fun validatePaymentPendingPurchases() {
        val openPurchases = purchaseRepository.findByStatus(PurchaseStatus.PENDING, PageRequest(0, 10000))
        if(openPurchases.content.isNotEmpty()) {
            log.info("Found {} open purchases, checking for payments...", openPurchases.content.size)
            openPurchases.forEach { purchase ->
                val payments = mercadoPagoApi.getPayments(purchase.transactionId!!)
                if(payments.results!!.isNotEmpty()) {
                    payments.results!!.forEach { payment ->
                        purchase.transactionStatus = payment.status.name
                        if(payment.status == Payment.Status.approved) {
                            purchase.status = PurchaseStatus.MAKING
                        } else {
                            if(purchase.stampDatetime!! + (1000 * 60 * 60) < System.currentTimeMillis()) {
                                purchase.status = PurchaseStatus.REJECTED
                            }
                        }
                    }
                    purchaseRepository.save(purchase)
                    log.info("Payment found saving purchase status.")
                } else if(purchase.stampDatetime!! + (1000 * 60 * 60) < System.currentTimeMillis()) {
                    purchase.status = PurchaseStatus.REJECTED
                    purchaseRepository.save(purchase)
                    log.info("Payment not found after an hour saving purchase status.")
                }
            }
        }
    }

}

interface MercadoPagoAPI {
    fun getPayments(externalReference: String) : PaymentResponse
}

@Component
@Profile("aws")
class MercadoPagoREST : MercadoPagoAPI {

    val restTemplate = RestTemplate()

    override fun getPayments(externalReference: String) : PaymentResponse {
        val accessToken = MercadoPago.SDK.getAccessToken()
        val url = StringBuilder("https://api.mercadopago.com/v1/payments/search")
                .append("?access_token=").append(accessToken)
                .append("&external_reference=").append(externalReference)
                .toString()
        return restTemplate.getForObject(url, PaymentResponse::class.java)
    }

}

@Component
@Profile("local")
class MercadoPagoStub : MercadoPagoAPI {

    override fun getPayments(externalReference: String) : PaymentResponse {
        val paging = PaymentResponseMetadata()
        val payment = Payment()
        payment.status = Payment.Status.approved
        val results = arrayListOf(payment)
        val paymentResponse = PaymentResponse()
        paymentResponse.paging = paging
        paymentResponse.results = results
        return paymentResponse
    }

}

open class PaymentResponse {
    var paging: PaymentResponseMetadata? = null
    var results: ArrayList<Payment>? = null
}

open class PaymentResponseMetadata {
    var total: Long? = null
    var limit: Long? = null
    var offset: Long? = null
}