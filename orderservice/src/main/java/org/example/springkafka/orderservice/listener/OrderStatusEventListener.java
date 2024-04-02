package org.example.springkafka.orderservice.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.springkafka.orderevent.OrderStatusEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class OrderStatusEventListener {

    private static final String ORDER_STATUS_TOPIC = "orderStatusTopic";

    private static final String KAFKA_GROUP = "orders";

    @KafkaListener(topics = ORDER_STATUS_TOPIC,
                   groupId = KAFKA_GROUP,
                   containerFactory = "orderEventConcurrentKafkaListenerContainerFactory")
    public void listen(@Payload OrderStatusEvent orderStatusEvent,
                       @Header(value = KafkaHeaders.RECEIVED_KEY, required = false)UUID key,
                       @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
                       @Header(KafkaHeaders.RECEIVED_TIMESTAMP) Long timestamp) {

        log.info("Received order status event: {}", orderStatusEvent);
        log.info("Key: {}; Partition: {}; Topic: {}, Timestamp: {}", key, partition, topic, timestamp);
    }
}
