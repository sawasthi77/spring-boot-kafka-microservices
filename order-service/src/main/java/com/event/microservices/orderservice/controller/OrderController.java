package com.event.microservices.orderservice.controller;

import com.event.microservices.basedomains.dto.OrderEvent;
import com.event.microservices.basedomains.dto.Orders;
import com.event.microservices.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class OrderController {

    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Orders orders){
        orders.setOrderId(UUID.randomUUID().toString());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("order event status is in pending stage");
        orderEvent.setOrder(orders);
        orderProducer.sendMessage(orderEvent);

        return "order placed successfully";
    }
}
