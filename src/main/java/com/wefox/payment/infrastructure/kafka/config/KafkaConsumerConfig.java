package com.wefox.payment.infrastructure.kafka.config;

import lombok.Data;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@Data
public abstract class KafkaConsumerConfig<T> {

    private final String groupId;
    private final String bootstrapAddress;
    private final Class<T> messageClass;

    public KafkaConsumerConfig(String groupId, String bootstrapAddress, Class<T> messageClass) {
        this.groupId = groupId;
        this.bootstrapAddress = bootstrapAddress;
        this.messageClass = messageClass;
    }

    protected ConsumerFactory<String, T> consumerFactory() {
        JsonDeserializer<T> messageDeserializer = new JsonDeserializer<>(messageClass);
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, messageDeserializer);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    protected ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, T> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
