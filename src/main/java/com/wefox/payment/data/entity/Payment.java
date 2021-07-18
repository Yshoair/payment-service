package com.wefox.payment.data.entity;

import com.wefox.payment.data.contract.IPaymentData;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter @ToString
@Entity
public @Table(name = "payments") class Payment implements IPaymentData {

  @Id @Column(name = "payment_id", length = 100)
  private String paymentId;

  @ToString.Exclude
  @ManyToOne @JoinColumn(name = "account_id", referencedColumnName = "account_id")
  private Account account;

  @Column(name = "payment_type", length = 150)
  private String paymentType;

  @Column(name = "credit_card", length = 100)
  private String creditCard;

  @Column(name = "amount")
  private int amount;

  @Column(name = "created_on")
  private Date createdOn;

  @Transient
  private int accountId;

  public int getAccountId() { return account != null ? account.getAccountId() : 0; }

  @Override
  public IPaymentData mapFrom(IPaymentData paymentData) {
    paymentId = paymentData.getPaymentId();
    account = new Account(paymentData.getAccountId());
    paymentType = paymentData.getPaymentType();
    creditCard = paymentData.getCreditCard();
    amount = paymentData.getAmount();
    createdOn = paymentData.getCreatedOn() != null ? paymentData.getCreatedOn() : new Date();
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Payment payment = (Payment) o;
    return amount == payment.amount
        && Objects.equals(paymentId, payment.paymentId)
        && Objects.equals(account.getAccountId(), payment.account.getAccountId())
        && Objects.equals(paymentType, payment.paymentType)
        && Objects.equals(creditCard, payment.creditCard)
        && Objects.equals(createdOn, payment.createdOn);
  }
}
