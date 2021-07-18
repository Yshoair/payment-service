package com.wefox.payment.data.contract;

import com.wefox.payment.infrastructure.exception.PaymentInternalException;

public interface IKafkaModel<T> {
    T parse(String json) throws PaymentInternalException;
}
