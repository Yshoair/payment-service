package com.wefox.payment.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wefox.payment.data.contract.IKafkaModel;
import com.wefox.payment.data.contract.IPaymentData;
import com.wefox.payment.infrastructure.exception.PaymentInternalException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Payment implements IPaymentData, IKafkaModel<Payment>, Serializable {

  @JsonProperty(value = "payment_id", index = 0)
  private String paymentId;

  @JsonProperty(value = "account_id", index = 1)
  private int accountId;

  @JsonProperty(value = "payment_type", index = 2)
  private String paymentType;

  @JsonProperty(value = "credit_card", index = 3)
  private String creditCard;

  @JsonProperty(value = "amount", index = 4)
  private int amount;

  @JsonIgnore private Date createdOn;

  @Override
  public IPaymentData mapFrom(IPaymentData paymentData) {
    return null;
  }

  @Override
  public Payment parse(String json) throws PaymentInternalException {
    try {
      ObjectMapper paymentMapper = new ObjectMapper();
      return paymentMapper.readValue(json, this.getClass());
    } catch (JsonProcessingException e) {
      throw new PaymentInternalException(
          "Unable to parse json payment message to model due to: " + e.getMessage(), "N/A");
    }
  }

  public String toJsonString() throws PaymentInternalException {
    ObjectMapper paymentMapper = new ObjectMapper();
    try {
      return paymentMapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
      throw new PaymentInternalException(
          "Failed to convert payment model to json string", paymentId);
    }
  }
}
