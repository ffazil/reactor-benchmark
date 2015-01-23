package com.tracebucket.benchmark.reactor.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import reactor.core.Reactor;
import reactor.event.Event;

/**
 * Created by ffl on 23-01-2015.
 */
@Component
public class MessageHandlerImpl implements MessageHandler{
    private static final Logger log = LoggerFactory.getLogger(MessageHandlerImpl.class);

    @Autowired
    private Reactor eventBus;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        log.info("Received  {}", message.getPayload());
        eventBus.notify("message", Event.<String>wrap(message.getPayload().toString()));
    }

}
