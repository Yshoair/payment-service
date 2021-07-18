package com.wefox.payment.service;

import com.wefox.payment.data.contract.IAccountData;
import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.data.entity.Account;
import com.wefox.payment.repository.contract.IAccountRepository;
import com.wefox.payment.repository.contract.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class PaymentService {
    @Autowired protected IAccountRepository accountRepository;
    @Autowired protected IPaymentRepository paymentRepository;

    public abstract void processPayment(IPaymentData paymentData);

    public IPaymentData storePayment(IPaymentData paymentData) {
        try {
            IPaymentData storedPayment = paymentRepository.save((com.wefox.payment.data.entity.Payment) paymentData);
            paymentRepository.flush();
            return storedPayment;
        } catch (Exception e) {
            // TODO throw custom exception
            e.printStackTrace();
        } return null;
    }

    public IAccountData updateAccount(IPaymentData paymentData) {
        Optional<Account> potentialAccount = accountRepository.findById(paymentData.getAccountId());
        Account account;
        if (potentialAccount.isPresent()) {
            account = potentialAccount.get();
            account.setLastPaymentDate(paymentData.getCreatedOn());
            IAccountData updatedAccount = accountRepository.save(account);
            accountRepository.flush();
            return updatedAccount;
        } else {
            // TODO throw custom exception
        } return null;
    }
}
