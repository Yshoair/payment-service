package com.wefox.payment.data.entity;

import com.wefox.payment.data.contract.IPaymentData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

  @Test
  void mapPaymentEntityFromPaymentModel_test() {
    IPaymentData paymentModel =
        new com.wefox.payment.data.model.Payment(
            "382b8005-da4e-4004-b064-5d920d47e14b",
            496,
            "online",
            "4847171163467501036",
            23,
            null);
    IPaymentData expectedPaymentEntity =
        new Payment(
            "382b8005-da4e-4004-b064-5d920d47e14b",
            new Account(496),
            "online",
            "4847171163467501036",
            23,
            null,
            0);
    assertEquals(expectedPaymentEntity, new Payment().mapFrom(paymentModel));
  }
}
