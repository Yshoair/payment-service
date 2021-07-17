package com.wefox.payment.data.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@Getter @Setter @RequiredArgsConstructor @ToString
@Entity
public @Table(name = "accounts") class Account {

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

  @Column(name = "created_on", columnDefinition = "Default now()")
  private Date createdOn;

  @ToString.Exclude
  @OneToMany(mappedBy = "account")
  private Collection<Payment> payments;

  public Account(int accountId) { this.accountId = accountId; }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    Account account = (Account) o;
    return Objects.equals(accountId, account.accountId);
  }
}
