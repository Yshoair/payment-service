package com.wefox.payment.infrastructure.kafka.consumer;

import com.wefox.payment.data.contract.IKafkaModel;
import com.wefox.payment.data.model.Payment;
import com.wefox.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
    topics = "${wefox.kafka.consumer.topic.payment.online.topic}",
    groupId = "${wefox.kafka.consumer.group.payment.online.id}")
public class KafkaOnlinePaymentConsumer extends KafkaConsumer<String> {

  @Autowired @Qualifier("onlinePayment")
  private PaymentService onlinePayment;
  private final IKafkaModel<Payment> payment = new Payment();

  @Override
  protected void messageHandler() {
    try {
      onlinePayment.processPayment(payment.parse(this.deserializedMessageClass));
    } catch (Exception e) {
      //TODO Centralized Exception handler catches the exception and send the error message accordingly
      e.printStackTrace();
    }
  }
}
