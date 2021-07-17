package com.wefox.payment.infrastructure.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:application.properties", ignoreResourceNotFound = true)
@Getter
public class ConfigurationManager {

  @Value("${spring.kafka.bootstrap-servers}")
  private String kafkaServer;

  @Value("${wefox.kafka.consumer.group.payment.offline.id}")
  private String kafkaOfflinePaymentGroupId;

  @Value("${wefox.kafka.consumer.group.payment.online.id}")
  private String kafkaOnlinePaymentGroupId;
}
