package com.wefox.payment.infrastructure.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
    topics = "${wefox.kafka.consumer.topic.payment.online.topic}",
    groupId = "${wefox.kafka.consumer.group.payment.online.id}")
public class KafkaOnlinePaymentConsumer extends KafkaConsumer<String> {

  @Override
  protected void messageHandler() {}
}
