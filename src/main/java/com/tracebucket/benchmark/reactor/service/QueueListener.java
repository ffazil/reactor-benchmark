package com.tracebucket.benchmark.reactor.service;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * Created by ffl on 23-01-2015.
 */
public interface QueueListener {
    void listen(Message<String> message);
}
