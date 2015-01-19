package com.tracebucket.benchmark.reactor.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = {"com.tracebucket.benchmark.reactor.controller"})
@EnableWebMvc
public class WebConfig {


}
