package com.wefox.payment.data.entity;

import com.wefox.payment.data.contract.IPaymentData;
import lombok.*;
import org.hibernate.Hibernate;

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

  @Column(name = "created_on", columnDefinition = "Default now()")
  private Date createdOn;

  @Transient
  private int accountId;

  @Override
  public IPaymentData mapFrom(IPaymentData paymentData) {
    paymentId = paymentData.getPaymentId();
    account = new Account(paymentData.getAccountId());
    paymentType = paymentData.getPaymentType();
    amount = paymentData.getAmount();
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Payment payment = (Payment) o;
    return Objects.equals(paymentId, payment.paymentId);
  }
}
