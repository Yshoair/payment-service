package com.wefox.payment.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wefox.payment.data.contract.IPaymentData;
import lombok.Data;

import java.util.Date;

@Data
public class IPayment implements IPaymentData {

  private @JsonProperty("payment_id") String paymentId;
  private @JsonProperty("account_id") int accountId;
  private @JsonProperty("payment_type") String paymentType;
  private @JsonProperty("credit_card") String creditCard;
  private @JsonProperty("amount") int amount;
  private Date createdAt;

  @Override
  public IPaymentData mapFrom(IPaymentData IPaymentData) {
    return null;
  }
}
