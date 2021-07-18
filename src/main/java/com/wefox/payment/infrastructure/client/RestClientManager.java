package com.wefox.payment.infrastructure.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;

/**
 * Provides implementations for POST/GET/PUT/DELETE rest operations across the system
 */
@Service
public class RestClientManager {

  @Autowired
  private RestOperations restTemplate;

  /**
   * Base method used by all rest types
   *
   * @param method values POST/GET/PUT/DELETE
   * @param url API url
   * @param headers Request headers
   * @param data  Request payload if exists
   * @param parameterizedTypeReference Response Type Reference if exists
   * @return Response entity of the type provided by the caller method
   */
  private <T> ResponseEntity<?> executeRequest(HttpMethod method,
                                               String url,
                                               HttpHeaders headers,
                                               T data,
                                               ParameterizedTypeReference<?> parameterizedTypeReference) {
    HttpEntity<?> requestEntity = new HttpEntity<>(data, headers);
    return restTemplate.exchange(url, method, requestEntity, parameterizedTypeReference);
  }


  public <T> ResponseEntity<?> executePost(String url,
                                           HttpHeaders headers,
                                           T data,
                                           ParameterizedTypeReference<?> parameterizedTypeReference) {
    return executeRequest(HttpMethod.POST, url, headers, data, parameterizedTypeReference);
  }

}
