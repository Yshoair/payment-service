package com.wefox.payment.infrastructure.exception.contract;

import com.wefox.payment.data.contract.IPaymentError;

public interface PaymentException {
    IPaymentError getPaymentError();
}
