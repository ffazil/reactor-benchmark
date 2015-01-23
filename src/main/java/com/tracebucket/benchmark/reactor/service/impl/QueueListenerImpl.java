package com.tracebucket.benchmark.reactor.service.impl;

import com.tracebucket.benchmark.reactor.service.QueueListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.dsl.support.Transformers;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import reactor.core.Reactor;
import reactor.event.Event;

/**
 * Created by ffl on 23-01-2015.
 */
@Component
public class QueueListenerImpl implements QueueListener{
    private static final Logger log = LoggerFactory.getLogger(QueueListenerImpl.class);

    @Autowired
    private Reactor eventBus;



    @Override
    @RabbitListener(queues = "benchmark.queue")
    public void listen(@Payload Message<String> message){
        log.info("Received  {}", message.getPayload());
        eventBus.notify("message", Event.<String>wrap(message.getPayload()));
    }
}
