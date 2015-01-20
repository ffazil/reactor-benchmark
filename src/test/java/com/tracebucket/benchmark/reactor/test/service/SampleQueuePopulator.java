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
        for (int i = 0; i < 100000; i++) {
            this.sampleGateway.sendMessage("Test Message " + i);
        }
    }

    @Configuration
    @EnableAutoConfiguration
    @ComponentScan("com.tracebucket.benchmark.reactor.test")
    public static class TestConfig {

    }
}
