package com.wefox.payment.service.contract;

import com.wefox.payment.data.contract.IPaymentData;

public interface IPayment {
    void processPayment(IPaymentData paymentData);
    IPaymentData storePayment(IPaymentData paymentData);
    void updateAccount(IPaymentData paymentData);
}
