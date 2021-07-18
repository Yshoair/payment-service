package com.wefox.payment.infrastructure.kafka.consumer;

import com.wefox.payment.data.contract.IKafkaModel;
import com.wefox.payment.data.model.Payment;
import com.wefox.payment.infrastructure.exception.PaymentDatabaseException;
import com.wefox.payment.infrastructure.exception.PaymentInternalException;
import com.wefox.payment.infrastructure.exception.PaymentNetworkException;
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

  @Autowired
  @Qualifier("onlinePayment")
  private PaymentService onlinePayment;

  private final IKafkaModel<Payment> payment = new Payment();

  @Override
  protected void messageHandler()
      throws PaymentNetworkException, PaymentDatabaseException, PaymentInternalException {
    onlinePayment.processPayment(payment.parse(this.deserializedMessageClass));
  }
}
