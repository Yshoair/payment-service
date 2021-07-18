package com.wefox.payment.infrastructure.kafka.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaHandler;

/**
 * Generic consumer class to be extended by any consumer class created in the project
 *
 * @param <T> the type of kafka message object to be deserialized
 */
public abstract class KafkaConsumer<T> {

  protected ConsumerRecord<String, T> message;
  protected T deserializedMessageClass;

  /**
   * Consumes the message from the topic and instantiates internal fields and calls the message
   * handler
   *
   * @param message the kafka recorde consumed from the topic
   * @param deserializedMessageClass the deserialized message consumed from the topic
   */
  @KafkaHandler
  protected void consumeMessage(ConsumerRecord<String, T> message, T deserializedMessageClass) {
    this.message = message;
    this.deserializedMessageClass = deserializedMessageClass;
    messageHandler();
  }

  /** Extending consumer classes will provide their own handler implementation here */
  protected abstract void messageHandler();
}
