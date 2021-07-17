package com.wefox.payment.data.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

  @Test
  void parsePaymentFromJson_test() {
    String paymentJson =
        "{\"payment_id\": \"382b8005-da4e-4004-b064-5d920d47e14b\", \"account_id\": 496, " +
        "\"payment_type\": \"online\", \"credit_card\": \"4847171163467501036\", " +
        "\"amount\": 23, \"delay\": 360}";
    Payment payment = new Payment(
            "382b8005-da4e-4004-b064-5d920d47e14b",
            496,
            "online",
            "4847171163467501036",
            23);
    assertEquals(payment, new Payment().parse(paymentJson));
  }
}
