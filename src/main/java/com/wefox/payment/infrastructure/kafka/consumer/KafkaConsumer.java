package com.wefox.payment.infrastructure.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaHandler;

public abstract class KafkaConsumer<T> {

  protected ConsumerRecord<String, T> message;
  protected T deserializedMessageClass;

  @KafkaHandler
  protected void consumeMessage(ConsumerRecord<String, T> message, T deserializedMessageClass) {
    this.message = message;
    this.deserializedMessageClass = deserializedMessageClass;
    messageHandler();
  }

  protected abstract void messageHandler();
}
