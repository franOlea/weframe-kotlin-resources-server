package org.weframe.kotlinresourcesserver.mercadopago;

import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class MercadoPagoConfiguration {

  @Value(value = "${mercado-pago.client-id}")
  private String clientID;
  @Value(value = "${mercado-pago.client-secret}")
  private String clientSecret;

  @PostConstruct
  public void init() throws MPException {
    MercadoPago.SDK.setClientSecret(clientSecret);
    MercadoPago.SDK.setClientId(clientID);
  }

  @Bean
  public MercadoPagoCredentials mercadoPagoCredentials() {
    return new MercadoPagoCredentials(clientID, clientSecret);
  }

}