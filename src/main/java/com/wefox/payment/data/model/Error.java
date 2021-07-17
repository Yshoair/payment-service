package com.wefox.payment.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wefox.payment.data.contract.IError;
import lombok.Data;

@Data
public class Error implements IError {

  private @JsonProperty("payment_id") String paymentId;
  private @JsonProperty("error") String Type;
  private @JsonProperty("error_description") String description;
}
