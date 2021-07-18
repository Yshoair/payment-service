package com.wefox.payment.infrastructure.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

@Service
public class RestClientManager {

  private @Autowired RestOperations restTemplate;

  // ---------------------------------- MAIN METHODS ----------------------------------

  private <T> ResponseEntity<?> executeRequest(HttpMethod method,
                                               String url,
                                               HttpHeaders headers,
                                               T data,
                                               ParameterizedTypeReference<?> parameterizedTypeReference) {
    HttpEntity<?> requestEntity = new HttpEntity<>(data, headers);
    return restTemplate.exchange(url, method, requestEntity, parameterizedTypeReference);
  }

  // ------------------------------------ POST ------------------------------------

  public <T> ResponseEntity<?> executePost(String url,
                                           HttpHeaders headers,
                                           T data,
                                           ParameterizedTypeReference<?> parameterizedTypeReference) {
    return executeRequest(HttpMethod.POST, url, headers, data, parameterizedTypeReference);
  }

  // ------------------------------------ POST ------------------------------------
}
