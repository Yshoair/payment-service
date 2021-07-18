package com.wefox.payment.infrastructure.exception;

import com.wefox.payment.data.contract.IPaymentError;
import com.wefox.payment.data.enums.PaymentErrorTypes;
import com.wefox.payment.data.model.PaymentError;
import com.wefox.payment.infrastructure.exception.contract.PaymentException;

public class PaymentNetworkException extends Exception implements PaymentException {

    private final IPaymentError paymentError;
    private final PaymentErrorTypes NETWORK = PaymentErrorTypes.NETWORK;

    public PaymentNetworkException(String message, String paymentId) {
        super(message);
        this.paymentError = new PaymentError(paymentId, NETWORK.getName(), message);
    }

    public PaymentNetworkException(String message, Throwable cause, String paymentId) {
        super(message, cause);
        this.paymentError = new PaymentError(paymentId, NETWORK.getName(), message);
    }

    public IPaymentError getPaymentError() {
        return paymentError;
    }
}
