package com.wefox.payment.infrastructure.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

/**
 * Manages system configurations
 */
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

  @Value("${wefox.external.payment.url}")
  private String externalPaymentUrl;

  @Value("${wefox.system.log.url}")
  private String loggerUrl;

  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }
}
