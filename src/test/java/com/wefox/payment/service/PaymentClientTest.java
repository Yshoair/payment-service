package com.wefox.payment.service;

import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.data.entity.Account;
import com.wefox.payment.data.entity.Payment;
import com.wefox.payment.infrastructure.client.RestClientManager;
import com.wefox.payment.infrastructure.config.ConfigurationManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PaymentClientTest {
  private IPaymentData payment;
  private String paymentUrl;
  private HttpHeaders headers;

  @BeforeEach
  public void setUp() {
    paymentUrl = "http://localhost:9000/payment";
    headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    payment = new Payment(
            "382b8005-da4e-4004-b064-5d920d47e14b",
            new Account(496),
            "online",
            "4847171163467501036",
            23,
            new Date(),
            0);
  }

  @Test
  void verifyPaymentStatusOk_Test() {
    ConfigurationManager configurationManager = mock(ConfigurationManager.class);
    RestClientManager restClientManager = mock(RestClientManager.class);
    when(configurationManager.getExternalPaymentUrl()).thenReturn(paymentUrl);
    when(restClientManager.executePost(
            paymentUrl,
            headers,
            payment,
            new ParameterizedTypeReference<Object>() {}
    )).thenReturn(new ResponseEntity<>(HttpStatus.OK));
    PaymentClient paymentClient = new PaymentClient(configurationManager, restClientManager);
    assertDoesNotThrow(() -> paymentClient.verifyPayment(payment));
  }
}
