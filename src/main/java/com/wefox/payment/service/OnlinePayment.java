package com.wefox.payment.service;

import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.service.contract.IOnlinePayment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("onlinePayment")
public class OnlinePayment extends PaymentService implements IOnlinePayment {

  @Override
  @Transactional
  public void processPayment(IPaymentData paymentData) {}

  @Override
  public void validate(IPaymentData paymentData) {}
}
