package com.wefox.payment.data.contract;

import java.util.Date;

public interface IPaymentData {

  String getPaymentId();

  int getAccountId();

  String getPaymentType();

  String getCreditCard();

  int getAmount();

  Date getCreatedAt();

  void setPaymentId(String paymentId);

  void setAccountId(int accountId);

  void setPaymentType(String paymentType);

  void setCreditCard(String creditCard);

  void setAmount(int amount);

  void setCreatedAt(Date createdAt);

  IPaymentData mapFrom(IPaymentData IPaymentData);
}
