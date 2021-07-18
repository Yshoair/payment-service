package com.wefox.payment.data.enums;

import lombok.Getter;

@Getter
public enum ErrorTypes {
  DATABASE("database"),
  NETWORK("network"),
  OTHER("other");

  private final String name;

  ErrorTypes(String name) {
    this.name = name;
  }
}
