package com.wefox.payment.data.model;

import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.data.entity.Account;
import com.wefox.payment.infrastructure.exception.PaymentInternalException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
  Payment payment;

  @BeforeEach
  public void setUp() {
    payment = new Payment(
            "382b8005-da4e-4004-b064-5d920d47e14b",
            496,
            "online",
            "4847171163467501036",
            23,
            null);
  }

  @Test
  void parsePaymentFromJson_test() throws PaymentInternalException {
    String paymentJson =
        "{\"payment_id\": \"382b8005-da4e-4004-b064-5d920d47e14b\", \"account_id\": 496, " +
        "\"payment_type\": \"online\", \"credit_card\": \"4847171163467501036\", " +
        "\"amount\": 23, \"delay\": 360}";
    assertEquals(payment, new Payment().parse(paymentJson));
  }

  @Test
  void convertPaymentModelToJsonString_test() throws PaymentInternalException {
    String paymentJson =
            "{\"payment_id\":\"382b8005-da4e-4004-b064-5d920d47e14b\",\"account_id\":496," +
            "\"payment_type\":\"online\",\"credit_card\":\"4847171163467501036\"," +
            "\"amount\":23}";
    assertEquals(paymentJson, payment.toJsonString());
  }
}
