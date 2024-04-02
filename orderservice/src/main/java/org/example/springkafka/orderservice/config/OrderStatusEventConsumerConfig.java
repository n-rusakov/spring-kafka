package org.example.springkafka.orderservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.example.springkafka.orderevent.OrderStatusEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class OrderStatusEventConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Bean
    public ConsumerFactory<String, OrderStatusEvent> orderStatusEventConsumerFactory(
            ObjectMapper objectMapper) {

        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        config.put(JsonDeserializer.TRUSTED_PACKAGES, "*");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(objectMapper));

    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, OrderStatusEvent> orderEventConcurrentKafkaListenerContainerFactory(
            ConsumerFactory<String, OrderStatusEvent> orderStatusEventConsumerFactory) {

        ConcurrentKafkaListenerContainerFactory<String, OrderStatusEvent> factory =
                new ConcurrentKafkaListenerContainerFactory<>();

        factory.setConsumerFactory(orderStatusEventConsumerFactory);

        return factory;
    }

}