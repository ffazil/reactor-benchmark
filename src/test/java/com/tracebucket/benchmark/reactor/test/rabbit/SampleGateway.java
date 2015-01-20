package com.tracebucket.benchmark.reactor.test.rabbit;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

/**
 * Created by ffl on 20-01-2015.
 */
@MessagingGateway
public interface SampleGateway {

    @Gateway(requestChannel = "message.input")
    void sendMessage(String message);
}
