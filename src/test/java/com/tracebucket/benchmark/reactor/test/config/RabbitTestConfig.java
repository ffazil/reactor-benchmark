package com.tracebucket.benchmark.reactor.test.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by ffl on 20-01-2015.
 */
@Configuration
public class RabbitTestConfig {

    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public Queue sampleQueue(){
        return new Queue("sample.queue", true, false, false);
    }

    @Bean
    public DirectExchange sampleExchange(){
        return new DirectExchange("sample.exchange", true, false);
    }

    @Bean
    public Binding sampleBinding(DirectExchange sampleExchange, Queue sampleQueue){
        return BindingBuilder.bind(sampleQueue())
                .to(sampleExchange)
                .with("exc.key");
    }

    @Bean
    @Primary
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate r = new RabbitTemplate(connectionFactory);
        r.setExchange(sampleExchange().getName());
        r.setChannelTransacted(true);
        r.setRoutingKey("exc.key");
        return r;
    }
}
