package org.example.springkafka.orderstatusservice.listener;

import lombok.RequiredArgsConstructor;
import org.example.springkafka.orderevent.OrderEvent;
import org.example.springkafka.orderevent.OrderStatusEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class OrderEventListener {

    private static final String ORDER_STATUS_TOPIC = "orderStatusTopic";

    private static final String ORDER_TOPIC = "orderTopic";

    private static final String KAFKA_GROUP = "orders";

    private final KafkaTemplate<String, OrderStatusEvent> kafkaTemplate;


    @KafkaListener(topics = ORDER_TOPIC,
            groupId = KAFKA_GROUP,
            containerFactory = "orderEventConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload OrderEvent orderEvent) {

        OrderStatusEvent orderStatusEvent = new OrderStatusEvent("CREATED", Instant.now());

        kafkaTemplate.send(ORDER_STATUS_TOPIC, orderStatusEvent);

    }

}
