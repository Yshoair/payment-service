package com.wefox.payment.infrastructure.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(
    topics = "${wefox.kafka.consumer.topic.payment.offline.topic}",
    groupId = "${wefox.kafka.consumer.group.payment.offline.id}")
public class KafkaOfflinePaymentConsumer extends KafkaConsumer<String> {

  @Override
  protected void messageHandler() {}
}
