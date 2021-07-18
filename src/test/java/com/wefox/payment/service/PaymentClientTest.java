package com.wefox.payment.service;

import com.wefox.payment.infrastructure.client.RestClientManager;
import com.wefox.payment.infrastructure.config.ConfigurationManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class PaymentClientTest {

  @TestConfiguration
  static class paymentClientTestContextConfiguration {
    @Bean
    public RestClientManager restClientManager() {
      return new RestClientManager();
    }
    @Bean
    public RestTemplate restTemplate() {
      return new RestTemplate();
    }
  }

  @Test
  void verifyPaymentStatusOk_Test() {
    ConfigurationManager configurationManager = mock(ConfigurationManager.class);
    RestClientManager restClientManager = mock(RestClientManager.class);
    when(configurationManager.getExternalPaymentUrl()).thenReturn("http://localhost:9000/payment");
    PaymentClient paymentClient = new PaymentClient(configurationManager, restClientManager);
    String paymentInfo =
        "\"payment_id\": \"382b8005-da4e-4004-b064-5d920d47e14b\", \"account_id\": 496, " +
        "\"payment_type\": \"online\", \"credit_card\": \"4847171163467501036\", " +
        "\"amount\": 23, \"delay\": 360";
    assertDoesNotThrow(() -> paymentClient.verifyPayment(paymentInfo));
  }
}
