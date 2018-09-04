package org.weframe.kotlinresourcesserver

import com.mercadopago.resources.Payment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.weframe.kotlinresourcesserver.purchase.PurchaseRepository
import java.security.Principal

@RestController
@RequestMapping("/purchases/transaction")
class MercadoPagoService(private val repo: PurchaseRepository) {

    @RequestMapping(value = [""], method = [RequestMethod.POST])
    fun process(@RequestParam("token") token: String,
                @RequestParam("payment_method_id") paymentMethodId: String,
                @RequestParam("installments") installments: Int,
                @RequestParam("issuer_id") issuerId: String,
                @RequestParam("purchaseId") purchaseId: Long,
                principal: Principal) : ResponseEntity<String> {
        val pendingPurchase = repo.findOne(purchaseId)
        val payment = Payment()
        payment.transactionAmount = 1f
        payment.setToken(token)
        payment.description = "Marco"
        payment.installments = installments
        payment.paymentMethodId = paymentMethodId
        payment.issuerId = issuerId
        payment.payer.email = principal.name
        payment.save()
        return ResponseEntity.ok(payment.status.toString())
    }

}