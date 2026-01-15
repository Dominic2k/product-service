package org.datpham.microservice.consumers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.datpham.microservice.consumers.dto.Order;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;
import tools.jackson.databind.ObjectMapper;

@Slf4j
@Component
public class OrderCreatedConsumer {

    @KafkaListener(topics = "order-created")
    public void handleOrderCreatedEvent (String orderString) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.readValue(orderString, Order.class);
        log.info("Received order created event: {}", order);
    }
}
