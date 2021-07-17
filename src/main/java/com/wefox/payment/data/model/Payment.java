package com.wefox.payment.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wefox.payment.data.contract.IKafkaModel;
import com.wefox.payment.data.contract.IPaymentData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class Payment implements IPaymentData, IKafkaModel<Payment> {

  private @JsonProperty("payment_id") String paymentId;
  private @JsonProperty("account_id") int accountId;
  private @JsonProperty("payment_type") String paymentType;
  private @JsonProperty("credit_card") String creditCard;
  private @JsonProperty("amount") int amount;
  private Date createdAt;

  public Payment(String paymentId, int accountId, String paymentType, String creditCard, int amount) {
    this.paymentId = paymentId;
    this.accountId = accountId;
    this.paymentType = paymentType;
    this.creditCard = creditCard;
    this.amount = amount;
  }

  @Override
  public IPaymentData mapFrom(IPaymentData IPaymentData) {
    return null;
  }

  @Override
  public Payment parse(String json) {
    return null;
  }
}
