package com.wefox.payment.service;

import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.data.entity.Account;
import com.wefox.payment.data.entity.Payment;
import com.wefox.payment.repository.contract.IAccountRepository;
import com.wefox.payment.repository.contract.IPaymentRepository;
import com.wefox.payment.service.contract.IOfflinePayment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class OfflinePayment implements IOfflinePayment {

  @Autowired private IAccountRepository accountRepository;
  @Autowired private IPaymentRepository paymentRepository;

  @Override
  @Transactional
  public void processPayment(IPaymentData paymentData) {
    IPaymentData payment = new Payment().mapFrom(paymentData);
    IPaymentData storedPayment = storePayment(payment);
    updateAccount(storedPayment);
  }

  @Override
  public IPaymentData storePayment(IPaymentData paymentData) {
    try {
      IPaymentData storedPayment = paymentRepository.save((Payment) paymentData);
      paymentRepository.flush();
      return storedPayment;
    } catch (Exception e) {
      // TODO throw custom exception
    } return null;
  }

  @Override
  public void updateAccount(IPaymentData paymentData) {
    Optional<Account> potentialAccount =
        accountRepository.findById(paymentData.getAccountId());
    Account account;
    if (potentialAccount.isPresent()) {
      account = potentialAccount.get();
      account.setLastPaymentDate(paymentData.getCreatedOn());
      accountRepository.save(account);
      accountRepository.flush();
    } else {
      // TODO throw custom exception
    }
  }
}
