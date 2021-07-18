package com.wefox.payment.infrastructure.kafka.consumer;

import com.wefox.payment.infrastructure.exception.PaymentDatabaseException;
import com.wefox.payment.infrastructure.exception.PaymentInternalException;
import com.wefox.payment.infrastructure.exception.PaymentNetworkException;
import com.wefox.payment.infrastructure.logger.LoggerClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Abstract Class to be extended by online & offline payments each providing his own payment handler service and
 * topics/group names
 */
public abstract class KafkaPaymentConsumer extends KafkaConsumer<String> {

  @Autowired
  private LoggerClient loggerClient;

  /**
   * Process the payment
   *
   * A centralized exception handler catches custom payment exceptions and sends them to the
   * logger api
   */
  @Override
  protected void messageHandler() {
    try {
      paymentHandler();
    } catch (PaymentNetworkException | PaymentDatabaseException | PaymentInternalException e) {
      loggerClient.logError(e.getPaymentError());
    }
  }

  /**
   * Payment processor each payment type provides his own implementation
   * @throws PaymentNetworkException
   * @throws PaymentDatabaseException
   * @throws PaymentInternalException
   */
  protected abstract void paymentHandler() throws PaymentNetworkException,
                                                  PaymentDatabaseException,
                                                  PaymentInternalException;
}
