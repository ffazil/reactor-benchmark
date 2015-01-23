package com.tracebucket.benchmark.reactor.test.service;

import com.tracebucket.benchmark.reactor.test.config.RabbitSampleOutbound;
import com.tracebucket.benchmark.reactor.test.config.RabbitTestConfig;
import com.tracebucket.benchmark.reactor.test.config.ServiceTestConfig;
import com.tracebucket.benchmark.reactor.test.rabbit.SampleGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by ffl on 20-01-2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class SampleQueuePopulator {

    @Autowired
    private SampleGateway sampleGateway;

    @Test
    public void populateTestData() throws Exception {
        Set<String> messages = generateData();
        messages.parallelStream()
                .forEach(message -> this.sampleGateway.sendMessage(message));
    }

    public Set<String> generateData(){
        Set<String> messages = new HashSet<>(0);

        for(int i = 0;i < 1000000; i++){
            messages.add("Test Message " + i);
        }
        return messages;
    }

    @Configuration
    @EnableAutoConfiguration
    @ComponentScan("com.tracebucket.benchmark.reactor.test")
    public static class TestConfig {

    }
}
