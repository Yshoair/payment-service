package com.wefox.payment.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wefox.payment.data.contract.IKafkaModel;
import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.infrastructure.exception.PaymentInternalException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor @NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment implements IPaymentData, IKafkaModel<Payment> {

  private @JsonProperty(value = "payment_id", index = 0) String paymentId;
  private @JsonProperty(value = "account_id", index = 1) int accountId;
  private @JsonProperty(value = "payment_type", index = 2) String paymentType;
  private @JsonProperty(value = "credit_card", index = 3) String creditCard;
  private @JsonProperty(value = "amount", index = 4) int amount;
  private @JsonIgnore Date createdOn;

  @Override
  public IPaymentData mapFrom(IPaymentData paymentData) {
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

  public String toJsonString() throws PaymentInternalException {
    ObjectMapper paymentMapper = new ObjectMapper();
    try {
      return paymentMapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new PaymentInternalException("Failed to convert payment model to json string", paymentId);
    }
  }
}
