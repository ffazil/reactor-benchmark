package com.tracebucket.benchmark.reactor.test.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by ffl on 20-01-2015.
 */
@Configuration
public class RabbitTestConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost("127.0.0.1");
        cachingConnectionFactory.setPort(5672);
        cachingConnectionFactory.setUsername("guest");
        cachingConnectionFactory.setPassword("guest");
        //cachingConnectionFactory.setConnectionCacheSize(100);
        cachingConnectionFactory.setCacheMode(CachingConnectionFactory.CacheMode.CHANNEL);
        cachingConnectionFactory.setChannelCacheSize(8);
        return cachingConnectionFactory;
    }

    @Bean
    public Queue sampleQueue(){
        return new Queue("benchmark.queue", false, false, false);
    }

    @Bean
    public DirectExchange sampleExchange(){
        return new DirectExchange("benchmark.exchange", false, false);
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
        RabbitTemplate r = new RabbitTemplate(connectionFactory());
        r.setExchange(sampleExchange().getName());
        r.setChannelTransacted(true);
        r.setRoutingKey("exc.key");
        return r;
    }
}
