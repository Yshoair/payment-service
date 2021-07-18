package com.wefox.payment.infrastructure.kafka.consumer;

import com.wefox.payment.infrastructure.exception.PaymentDatabaseException;
import com.wefox.payment.infrastructure.exception.PaymentInternalException;
import com.wefox.payment.infrastructure.exception.PaymentNetworkException;
import com.wefox.payment.infrastructure.logger.LoggerClient;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;

public abstract class KafkaConsumer<T> {

  protected ConsumerRecord<String, T> message;
  protected T deserializedMessageClass;
  private @Autowired LoggerClient loggerClient;

  @KafkaHandler
  protected void consumeMessage(ConsumerRecord<String, T> message, T deserializedMessageClass) {
    this.message = message;
    this.deserializedMessageClass = deserializedMessageClass;
    try {
      messageHandler();
    } catch (PaymentNetworkException | PaymentDatabaseException | PaymentInternalException e) {
      loggerClient.logError(e.getPaymentError());
    }
  }

  protected abstract void messageHandler()
      throws PaymentNetworkException, PaymentDatabaseException, PaymentInternalException;
}
