package com.wefox.payment.data.contract;

public interface IKafkaModel<T> {
    T parse(String json);
}
