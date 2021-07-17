package com.wefox.payment.service;

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
    public void storePayment(IPaymentData paymentData) {

    }

    @Override
    public void updateAccount(IPaymentData paymentData) {

    }
}
