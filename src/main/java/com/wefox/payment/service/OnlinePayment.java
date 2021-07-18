package com.wefox.payment.service;

import com.wefox.payment.data.contract.IAccountData;
import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.data.entity.Payment;
import com.wefox.payment.infrastructure.exception.PaymentDatabaseException;
import com.wefox.payment.infrastructure.exception.PaymentNetworkException;
import com.wefox.payment.service.contract.IOnlinePayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("onlinePayment")
public class OnlinePayment extends PaymentService implements IOnlinePayment {

  @Autowired
  private PaymentClient paymentClient;

  @Override
  @Transactional
  public void processPayment(IPaymentData paymentData) throws PaymentNetworkException, PaymentDatabaseException {
    this.validate(paymentData);
    IPaymentData payment = new Payment().mapFrom(paymentData);
    IPaymentData storedPayment = storePayment(payment);
    IAccountData updatedAccount = updateAccount(storedPayment);
  }

  @Override
  public void validate(IPaymentData paymentData) throws PaymentNetworkException {
    paymentClient.verifyPayment(paymentData);
  }
}
