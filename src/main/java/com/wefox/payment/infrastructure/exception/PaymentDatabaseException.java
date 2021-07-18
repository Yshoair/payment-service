package com.wefox.payment.infrastructure.exception;

import com.wefox.payment.data.contract.IPaymentError;
import com.wefox.payment.data.enums.PaymentErrorTypes;
import com.wefox.payment.data.model.PaymentError;

public class PaymentDatabaseException extends Exception {

  private final IPaymentError paymentError;
  private final PaymentErrorTypes DATABASE = PaymentErrorTypes.DATABASE;

  public PaymentDatabaseException(String message, String paymentId) {
    super(message);
    this.paymentError = new PaymentError(paymentId, DATABASE.getName(), message);
  }

  public PaymentDatabaseException(String message, Throwable cause, String paymentId) {
    super(message, cause);
    this.paymentError = new PaymentError(paymentId, DATABASE.getName(), message);
  }

  public IPaymentError getPaymentError() {
    return paymentError;
  }
}
