package com.wefox.payment.service;

import com.wefox.payment.data.contract.IAccountData;
import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.service.contract.IOnlinePayment;
import org.springframework.stereotype.Service;

@Service
public class OnlinePayment implements IOnlinePayment {

    @Override
    public void processPayment(IPaymentData paymentData) {

    }

    @Override
    public void validate(IPaymentData paymentData) {

    }

    @Override
    public IPaymentData storePayment(IPaymentData paymentData) {
        return null;
    }

    @Override
    public IAccountData updateAccount(IPaymentData paymentData) {
        return null;
    }
}
