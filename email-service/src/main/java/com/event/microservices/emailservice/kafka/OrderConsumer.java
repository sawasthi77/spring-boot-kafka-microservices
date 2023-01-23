package com.event.microservices.emailservice.kafka;

import com.event.microservices.basedomains.dto.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(OrderEvent orderEvent) {
        logger.info(String.format("Order event received in email service %s ", orderEvent.toString()));

        //TODO:save the email to the customer about the order
    }

}
