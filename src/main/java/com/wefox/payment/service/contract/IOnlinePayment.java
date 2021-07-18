package com.wefox.payment.service.contract;

import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.infrastructure.exception.PaymentNetworkException;

public interface IOnlinePayment {
    void validate(IPaymentData paymentData) throws PaymentNetworkException;
}
