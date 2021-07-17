package com.wefox.payment.infrastructure.kafka.config;

import com.wefox.payment.data.model.Payment;
import com.wefox.payment.infrastructure.config.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;

@EnableKafka
@Configuration
public class KafkaOfflinePaymentConsumerConfig extends KafkaConsumerConfig<Payment> {

  @Autowired
  public KafkaOfflinePaymentConsumerConfig(ConfigurationManager configManager) {
    super(
        configManager.getKafkaOfflinePaymentGroupId(),
        configManager.getKafkaServer(),
        Payment.class);
  }

  @Override
  @Bean("OfflinePaymentContainerFactory")
  public ConcurrentKafkaListenerContainerFactory<String, Payment> kafkaListenerContainerFactory() {
    return super.kafkaListenerContainerFactory();
  }
}
