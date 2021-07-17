package com.wefox.payment.service;

import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.data.entity.Account;
import com.wefox.payment.data.entity.Payment;
import com.wefox.payment.repository.contract.IAccountRepository;
import com.wefox.payment.repository.contract.IPaymentRepository;
import com.wefox.payment.service.contract.IOfflinePayment;
import com.wefox.payment.service.contract.IPayment;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class OfflinePaymentTest {

  @Autowired private IOfflinePayment offlinePayment;
  @Autowired private IAccountRepository accountRepository;
  @Autowired private IPaymentRepository paymentRepository;

  @TestConfiguration
  static class OfflinePaymentServiceImplTestContextConfiguration {
    @Bean
    public IOfflinePayment offlinePayment() {
      return new OfflinePayment();
    }
  }

  @Test
  void offlinePaymentStoredCorrectly_Test() {
    IPaymentData payment = new Payment(
            "382b8005-da4e-4004-b064-5d920d47e14b",
            new Account(496),
            "online",
            "4847171163467501036",
            23,
            null,
            0);
    payment = offlinePayment.storePayment(payment);
    IPaymentData persistedPaymentData = paymentRepository.findById("382b8005-da4e-4004-b064-5d920d47e14b").
            orElse(new Payment());
    assertEquals(payment, persistedPaymentData);
  }

  @Test
  void offlinePaymentTransactionAccountUpdatedCorrectly_Test() {}
}
