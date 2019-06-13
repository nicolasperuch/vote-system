package com.github.nicolasperuch;

import com.github.nicolasperuch.config.RabbitVoteConfig;
import com.github.nicolasperuch.service.SessionEndedService;
import com.github.nicolasperuch.service.SessionStartedService;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EntryPoint extends RabbitVoteConfig {

    @Autowired
    private SessionStartedService sessionStartedService;
    @Autowired
    private SessionEndedService sessionEndedService;

    @RabbitListener(queues = {SESSION_STARTED_QUEUE})
    public void receiveSessionStartedEvent(@Payload Message message){
        sessionStartedService.openRulingForVote(message);
    }

    @RabbitListener(queues = {SESSION_ENDED_QUEUE})
    public void receiveSessionEndedEvent(@Payload Message message){
        sessionEndedService.closeRuling(message);
    }
}