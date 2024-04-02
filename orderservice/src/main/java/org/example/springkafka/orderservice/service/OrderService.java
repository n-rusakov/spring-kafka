package org.example.springkafka.orderservice.service;

import lombok.RequiredArgsConstructor;
import org.example.springkafka.orderevent.OrderEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final String ORDER_TOPIC = "orderTopic";

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void send(OrderEvent orderEvent) {
        kafkaTemplate.send(ORDER_TOPIC, orderEvent);
    }
}
