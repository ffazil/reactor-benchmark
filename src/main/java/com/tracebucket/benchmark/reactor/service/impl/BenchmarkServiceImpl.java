package com.tracebucket.benchmark.reactor.service.impl;

import com.tracebucket.benchmark.reactor.service.BenchmarkService;
import com.tracebucket.benchmark.reactor.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.event.Event;
import reactor.spring.context.annotation.Consumer;
import reactor.spring.context.annotation.Selector;

/**
 * Created by ffl on 19-01-2015.
 */
@Consumer
public class BenchmarkServiceImpl implements BenchmarkService{
    private static final Logger log = LoggerFactory.getLogger(BenchmarkServiceImpl.class);

    @Override
    @Selector(value = "benchmark", reactor = "@eventBus")
    public void benchmark(Event<Integer> seedEvent) {
        log.info("Received event " + seedEvent.getData());
        try {
            Thread.sleep(300);
            log.info("Processed event " + seedEvent.getData());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    @Selector(value = "message", reactor = "@eventBus")
    public void payload(Event<Object> payload) {
        log.info("Processed event " + payload.getData().toString());
    }
}
