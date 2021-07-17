package com.wefox.payment.data.contract;

import java.util.Date;

public interface IAccountData {

  int getAccountId();
  String getName();
  String getEmail();
  Date getBirthDate();
  Date getLastPaymentDate();
  Date getCreatedOn();

  void setAccountId(int accountId);
  void setName(String name);
  void setEmail(String email);
  void setBirthDate(Date birthDate);
  void setLastPaymentDate(Date lastPaymentDate);
  void setCreatedOn(Date createdOn);

  IAccountData mapFrom(IAccountData accountData);
}
