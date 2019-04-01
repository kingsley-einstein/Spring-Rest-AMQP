package com.spring.rest.amqp.broking;

import com.spring.rest.amqp.broking.event.ProfileCreatedEvent;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BrokingDispatcher {

    @Autowired
    private RabbitTemplate template;

    private String exchangeKey;

    private String profileCreatedKey;

    public BrokingDispatcher(@Value("${service.exchangeKey}") String exchangeKey,
            @Value("${service.profileCreatedKey}") String profileCreatedKey) {
        this.exchangeKey = exchangeKey;
        this.profileCreatedKey = profileCreatedKey;
    }

    public void dispatchProfileCreatedEvent(ProfileCreatedEvent event) {
        System.out.println("Sending profile created event > ...");
        template.convertAndSend(exchangeKey, profileCreatedKey, event);
    }
}