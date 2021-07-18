package com.wefox.payment.service;

import com.wefox.payment.data.contract.IAccountData;
import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.data.entity.Account;
import com.wefox.payment.infrastructure.exception.PaymentDatabaseException;
import com.wefox.payment.infrastructure.exception.PaymentNetworkException;
import com.wefox.payment.repository.contract.IAccountRepository;
import com.wefox.payment.repository.contract.IPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public abstract class PaymentService {
  @Autowired protected IAccountRepository accountRepository;
  @Autowired protected IPaymentRepository paymentRepository;

  public abstract void processPayment(IPaymentData paymentData)
      throws PaymentNetworkException, PaymentDatabaseException;

  public IPaymentData storePayment(IPaymentData paymentData) throws PaymentDatabaseException {
    try {
      IPaymentData storedPayment =
          paymentRepository.save((com.wefox.payment.data.entity.Payment) paymentData);
      paymentRepository.flush();
      return storedPayment;
    } catch (Exception e) {
      e.printStackTrace();
      throw new PaymentDatabaseException(
          "Failed to persist payment due to: " + e.getMessage(), paymentData.getPaymentId());
    }
  }

  public IAccountData updateAccount(IPaymentData paymentData) throws PaymentDatabaseException {
    Optional<Account> potentialAccount = accountRepository.findById(paymentData.getAccountId());
    Account account;
    if (potentialAccount.isPresent()) {
      account = potentialAccount.get();
      account.setLastPaymentDate(paymentData.getCreatedOn());
      IAccountData updatedAccount = accountRepository.save(account);
      accountRepository.flush();
      return updatedAccount;
    } else {
      throw new PaymentDatabaseException(
          "Account number not found: " + paymentData.getAccountId(), paymentData.getPaymentId());
    }
  }
}
