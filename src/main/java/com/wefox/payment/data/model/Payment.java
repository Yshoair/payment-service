package com.wefox.payment.data.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wefox.payment.data.contract.IKafkaModel;
import com.wefox.payment.data.contract.IPaymentData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
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
    try {
      ObjectMapper paymentMapper = new ObjectMapper();
      return paymentMapper.readValue(json, this.getClass());
    } catch (JsonProcessingException e) {
      //TODO Add Exception for invalid data and send the error to the logger
      e.printStackTrace();
    }
    return null;
  }
}
