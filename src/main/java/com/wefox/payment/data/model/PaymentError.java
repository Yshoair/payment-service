package com.wefox.payment.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wefox.payment.data.contract.IPaymentError;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data @AllArgsConstructor
public class PaymentError implements IPaymentError {

  private @JsonProperty("payment_id") String paymentId;
  private @JsonProperty("error") String Type;
  private @JsonProperty("error_description") String description;
}
