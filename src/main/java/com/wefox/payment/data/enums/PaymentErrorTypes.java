package com.wefox.payment.data.enums;

import lombok.Getter;

@Getter
public enum PaymentErrorTypes {
  DATABASE("database"),
  NETWORK("network"),
  OTHER("other");

  private final String name;

  PaymentErrorTypes(String name) {
    this.name = name;
  }
}
