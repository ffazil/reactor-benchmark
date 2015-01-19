package com.tracebucket.benchmark.reactor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ScopedProxyMode;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import reactor.spring.context.config.EnableReactor;


@Configuration
@ComponentScan(basePackages = {"com.tracebucket.benchmark.reactor.service.impl"}, scopedProxy = ScopedProxyMode.INTERFACES)
@EnableReactor
public class ServiceConfig {

    @Bean
    public Reactor eventBus(Environment env) {
        // implicit Environment is injected into bean def method
        return Reactors.reactor().env(env).get();
    }



}
