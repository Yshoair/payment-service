package com.wefox.payment.service;

import com.wefox.payment.data.contract.IAccountData;
import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.data.entity.Account;
import com.wefox.payment.data.entity.Payment;
import com.wefox.payment.infrastructure.exception.PaymentDatabaseException;
import com.wefox.payment.repository.contract.IPaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class InternalPaymentTest {

  private @Autowired PaymentService offlinePayment;
  private @Autowired IPaymentRepository paymentRepository;
  private IPaymentData payment;
  private Date currentDate;

  @TestConfiguration
  static class OfflinePaymentServiceImplTestContextConfiguration {
    @Bean
    public PaymentService offlinePayment() {
      return new OfflinePayment();
    }
  }

  @BeforeEach
  public void setUp() {
    currentDate = new Date();
    payment = new Payment(
            "382b8005-da4e-4004-b064-5d920d47e14b",
            new Account(496),
            "online",
            "4847171163467501036",
            23,
            currentDate,
            0);
  }

  @Test
  void offlinePaymentStoredCorrectly_Test() throws PaymentDatabaseException {
    offlinePayment.storePayment(payment);
    IPaymentData persistedPaymentData = paymentRepository
            .findById("382b8005-da4e-4004-b064-5d920d47e14b")
            .orElse(new Payment());
    assertEquals(payment, persistedPaymentData);
  }

  @Test
  void accountLastPaymentDateUpdatedCorrectly_Test() throws PaymentDatabaseException {
    IAccountData updatedAccount = offlinePayment.updateAccount(payment);
    assertEquals(currentDate, updatedAccount.getLastPaymentDate());
  }
}
