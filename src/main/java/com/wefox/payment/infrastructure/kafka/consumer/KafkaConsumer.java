package com.wefox.payment.infrastructure.kafka.consumer;

import com.wefox.payment.infrastructure.exception.PaymentDatabaseException;
import com.wefox.payment.infrastructure.exception.PaymentInternalException;
import com.wefox.payment.infrastructure.exception.PaymentNetworkException;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaHandler;

public abstract class KafkaConsumer<T> {

  protected ConsumerRecord<String, T> message;
  protected T deserializedMessageClass;

  @KafkaHandler
  protected void consumeMessage(ConsumerRecord<String, T> message, T deserializedMessageClass) {
    this.message = message;
    this.deserializedMessageClass = deserializedMessageClass;
    try {
      messageHandler();
    } catch (PaymentNetworkException | PaymentDatabaseException | PaymentInternalException e) {
      e.printStackTrace();
    }
  }

  protected abstract void messageHandler()
      throws PaymentNetworkException, PaymentDatabaseException, PaymentInternalException;
}
