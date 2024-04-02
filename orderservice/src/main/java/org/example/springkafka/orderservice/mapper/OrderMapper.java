package org.example.springkafka.orderservice.mapper;

import org.example.springkafka.orderevent.OrderEvent;
import org.example.springkafka.orderservice.dto.OrderDto;
import org.springframework.stereotype.Component;

@Component
public class OrderMapper {

    public OrderEvent toEvent(OrderDto orderDto) {
        OrderEvent orderEvent = new OrderEvent();

        orderEvent.setProduct(orderDto.getProduct());
        orderEvent.setQuality(orderDto.getQuality());

        return orderEvent;
    }
}
