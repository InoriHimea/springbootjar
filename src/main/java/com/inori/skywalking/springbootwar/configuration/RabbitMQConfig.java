package com.inori.skywalking.springbootwar.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class RabbitMQConfig {

    @Bean
    public Queue UserQueue() {
        log.info("创建用户队列");
        return new Queue("UserQueue", true);
    }

    @Bean
    public Queue ResultQueue() {
        return new Queue("ResultQueue", true);
    }

    @Bean
    public DirectExchange userExchange() {
        log.info("创建用户交换机");
        return new DirectExchange("UserExchange");
    }

    @Bean
    public Binding bindDirect() {
        log.info("绑定UserRouting");
        return BindingBuilder.bind(UserQueue()).to(userExchange()).with("UserRouting");
    }
}
