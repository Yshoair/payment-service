package com.wefox.payment.service;

import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.repository.contract.IAccountRepository;
import com.wefox.payment.repository.contract.IPaymentRepository;
import com.wefox.payment.service.contract.IOfflinePayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OfflinePayment implements IOfflinePayment {

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IPaymentRepository paymentRepository;


    @Override
    public void processPayment(IPaymentData paymentData) {

    }

    @Override
    public void storePayment(IPaymentData paymentData) {

    }

    @Override
    public void updateAccount(IPaymentData paymentData) {

    }
}
