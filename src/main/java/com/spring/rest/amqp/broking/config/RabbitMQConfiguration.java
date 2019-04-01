package com.spring.rest.amqp.broking.config;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    public TopicExchange exchange(@Value("${service.exchangeKey}") String exchanger) {
        return new TopicExchange(exchanger);
    }
}