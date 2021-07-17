package com.wefox.payment.data.contract;

public interface IError {

  String getPaymentId();

  String getType();

  String getDescription();

  void setPaymentId(String paymentId);

  void setType(String Type);

  void setDescription(String description);
}
