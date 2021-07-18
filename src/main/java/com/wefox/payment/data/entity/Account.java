package com.wefox.payment.data.entity;

import com.wefox.payment.data.contract.IAccountData;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Getter @Setter @RequiredArgsConstructor @ToString
@Entity
public @Table(name = "accounts") class Account implements IAccountData {

  @Id @Column(name = "account_id")
  private int accountId;

  @Column(name = "name", length = 150)
  private String name;

  @Column(name = "email", length = 100)
  private String email;

  @Column(name = "birthdate")
  private Date birthDate;

  @Column(name = "last_payment_date")
  private Date lastPaymentDate;

  @Column(name = "created_on")
  private Date createdOn;

  @ToString.Exclude
  @OneToMany(mappedBy = "account")
  private Collection<Payment> payments;

  public Account(int accountId) { this.accountId = accountId; }

  @Override
  public IAccountData mapFrom(IAccountData accountData) {
    return null;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Account account = (Account) o;
    return accountId == account.accountId
        && Objects.equals(name, account.name)
        && Objects.equals(email, account.email)
        && Objects.equals(birthDate, account.birthDate)
        && Objects.equals(lastPaymentDate, account.lastPaymentDate)
        && Objects.equals(createdOn, account.createdOn)
        && Objects.equals(payments, account.payments);
  }
}
