package com.wefox.payment.service;

import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.data.entity.Payment;
import com.wefox.payment.infrastructure.exception.PaymentDatabaseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("offlinePayment")
public class OfflinePayment extends PaymentService {

  @Override
  @Transactional
  public void processPayment(IPaymentData paymentData) throws PaymentDatabaseException {
    IPaymentData payment = new Payment().mapFrom(paymentData);
    IPaymentData storedPayment = storePayment(payment);
    updateAccount(storedPayment);
  }
}
