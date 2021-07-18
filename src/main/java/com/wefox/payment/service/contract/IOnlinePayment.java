package com.wefox.payment.service.contract;

import com.wefox.payment.data.contract.IPaymentData;

public interface IOnlinePayment {
    void validate(IPaymentData paymentData);
}
