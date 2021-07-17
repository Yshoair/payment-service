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
public class KafkaOnlinePaymentConsumerConfig extends KafkaConsumerConfig<Payment> {

  @Autowired
  public KafkaOnlinePaymentConsumerConfig(ConfigurationManager configManager) {
    super(
        configManager.getKafkaOnlinePaymentGroupId(),
        configManager.getKafkaServer(),
        Payment.class);
  }

  @Override
  @Bean("OnlinePaymentContainerFactory")
  protected ConcurrentKafkaListenerContainerFactory<String, Payment> kafkaListenerContainerFactory() {
    return super.kafkaListenerContainerFactory();
  }
}
