package org.weframe.kotlinresourcesserver.mercadopago

import com.mercadopago.MercadoPago
import com.mercadopago.resources.Payment
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import org.weframe.kotlinresourcesserver.purchase.PurchaseRepository


//@
@RestController
class MercadoPagoPurchaseChecker(private val purchaseRepository: PurchaseRepository,
                                 private val mercadoPagoApi: MercadoPagoAPI) {

//    @Scheduled(fixedRate = 10_000)
    @RequestMapping(path = ["/test"], method = [RequestMethod.GET])
    fun validatePaymentPendindPurchases() {
        val openPurchases = purchaseRepository.findByStatus("OPEN", PageRequest(0, 100))
        openPurchases.forEach { purchase ->
            val payments = mercadoPagoApi.getPayments(purchase.transactionId!!)
            payments.results!!.forEach { payment ->
                purchase.status = payment.status.name
            }
            purchaseRepository.save(purchase)
        }
    }

    fun test() {
        val accessToken = MercadoPago.SDK.getAccessToken()
        
    }

}

interface MercadoPagoAPI {
    fun getPayments(externalReference: String) : PaymentResponse
}

@Component
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

open class PaymentResponse {
    var paging: PaymentResponseMetadata? = null
    var results: ArrayList<Payment>? = null
}

open class PaymentResponseMetadata {
    var total: Long? = null
    var limit: Long? = null
    var offset: Long? = null
}