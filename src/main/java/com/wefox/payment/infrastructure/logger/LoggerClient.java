package com.wefox.payment.infrastructure.logger;

import com.wefox.payment.data.contract.IPaymentError;
import com.wefox.payment.infrastructure.client.RestClientManager;
import com.wefox.payment.infrastructure.config.ConfigurationManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class LoggerClient {
  private final ConfigurationManager configurationManager;
  private final RestClientManager restClientManager;

  @Autowired
  public LoggerClient(ConfigurationManager configurationManager,
                      RestClientManager restClientManager) {
    this.configurationManager = configurationManager;
    this.restClientManager = restClientManager;
  }

  public void logError(IPaymentError error) {
    String loggerUrl = configurationManager.getLoggerUrl();
    HttpHeaders headers = new HttpHeaders();
    headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    ResponseEntity<?> apiResponse;
    try {
      apiResponse =
          restClientManager.executePost(
              loggerUrl, headers, error, new ParameterizedTypeReference<Object>() {});
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
