package com.inori.skywalking.springbootwar.configuration;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

@Configuration
public class ActiveMQConfig {

    @Bean
    public Queue userQueue() {
        return new ActiveMQQueue("UserQueue");
    }
}
