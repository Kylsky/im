package com.kyle.im.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RabbitmqConfig {

    private final static String message = "web.socket.message";

    @Bean("queue")
    public Queue queueMessage() {
        return new Queue(RabbitmqConfig.message);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("im");
    }

    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, DirectExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("im");
    }
}
