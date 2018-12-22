package org.weframe.kotlinresourcesserver.mercadopago;

public class MercadoPagoCredentials {
  private final String clientId;
  private final String clientSecret;

  public MercadoPagoCredentials(final String clientId, final String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  private String getClientId() {
    return clientId;
  }

  private String getClientSecret() {
    return clientSecret;
  }
}
