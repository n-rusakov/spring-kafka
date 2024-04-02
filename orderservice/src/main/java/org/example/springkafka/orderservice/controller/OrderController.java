package org.example.springkafka.orderservice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springkafka.orderservice.dto.OrderDto;
import org.example.springkafka.orderservice.mapper.OrderMapper;
import org.example.springkafka.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    private final OrderMapper orderMapper;

    @PostMapping
    ResponseEntity<Void> send(@RequestBody OrderDto orderDto) {

        orderService.send(orderMapper.toEvent(orderDto));

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
