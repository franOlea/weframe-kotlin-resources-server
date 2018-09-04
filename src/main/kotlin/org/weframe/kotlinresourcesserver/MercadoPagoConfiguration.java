package org.weframe.kotlinresourcesserver;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MercadoPagoConfiguration {

  @Value(value = "${mercado-pago.plublic-key}")
  private String publicKey;
  @Value(value = "${mercado-pago.private-key}")
  private String privateKey;

  @PostConstruct
  public void init() throws MPException {
    MercadoPago.SDK.setClientSecret(privateKey);
    MercadoPago.SDK.setClientId(publicKey);
  }

}
