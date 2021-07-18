package com.wefox.payment.infrastructure.exception;

import com.wefox.payment.data.contract.IPaymentError;
import com.wefox.payment.data.enums.PaymentErrorTypes;
import com.wefox.payment.data.model.PaymentError;

public class PaymentInternalException extends Exception {

    private final IPaymentError paymentError;
    private final PaymentErrorTypes OTHER = PaymentErrorTypes.OTHER;

    public PaymentInternalException(String message, String paymentId) {
        super(message);
        this.paymentError = new PaymentError(paymentId, OTHER.getName(), message);
    }

    public PaymentInternalException(String message, Throwable cause, String paymentId) {
        super(message, cause);
        this.paymentError = new PaymentError(paymentId, OTHER.getName(), message);
    }

    public IPaymentError getPaymentError() {
        return paymentError;
    }
}
