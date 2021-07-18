package com.wefox.payment.service;

import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.data.model.Payment;
import com.wefox.payment.infrastructure.client.RestClientManager;
import com.wefox.payment.infrastructure.config.ConfigurationManager;
import com.wefox.payment.infrastructure.exception.PaymentNetworkException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PaymentGateway {
  private final ConfigurationManager configurationManager;
  private final RestClientManager restClientManager;

  @Autowired
  public PaymentGateway(ConfigurationManager configurationManager, RestClientManager restClientManager) {
    this.configurationManager = configurationManager;
    this.restClientManager = restClientManager;
  }

  public void verifyPayment(IPaymentData payment) throws PaymentNetworkException {
    String externalPaymentUrl = configurationManager.getExternalPaymentUrl();
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    ResponseEntity<?> apiResponse;
    try {
      apiResponse = restClientManager.executePost(
              externalPaymentUrl,
              headers,
              payment,
              new ParameterizedTypeReference<Object>() {}
      );
    } catch (Exception e) {
      e.printStackTrace();
      throw new PaymentNetworkException(
          "Failed to call external payment api due to: " + e.getMessage(),
              payment.getPaymentId());
    }
    int statusCode = apiResponse.getStatusCodeValue();
    if (statusCode != HttpStatus.OK.value())
      throw new PaymentNetworkException("Invalid online payment", payment.getPaymentId());
  }
}
