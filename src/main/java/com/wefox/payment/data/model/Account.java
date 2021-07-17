package com.wefox.payment.data.model;

import com.wefox.payment.data.contract.IAccountData;
import lombok.Data;

import java.util.Date;

@Data
public class Account implements IAccountData {

  private int accountId;
  private String name;
  private String email;
  private Date birthDate;
  private Date lastPaymentDate;
  private Date createdOn;

  @Override
  public IAccountData mapFrom(IAccountData accountData) {
    return null;
  }
}
