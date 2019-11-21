package com.inori.skywalking.springbootwar.mq.rabbit.task;

import com.inori.skywalking.springbootwar.mapper.UserMapper;
import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.thread.ExecutorServiceManager;
import com.inori.skywalking.springbootwar.thread.NamedRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
/*@Component("RabbitMQTask")*/
public class UserProviderTask {

    private static final ScheduledExecutorService service = ExecutorServiceManager.getSingleScheduledExecutorService("RabbitMQ生产者");

    @Autowired
    private UserMapper mapper;

    @Autowired
    private RabbitTemplate template;

    /*@PostConstruct*/
    public void sendMessage2Queue() {
        List<User> userInfo = mapper.getUserInfo(null);
        final AtomicInteger times = new AtomicInteger(1);

        service.scheduleWithFixedDelay(new NamedRunnable() {

            @Override
            public void runAfter() {
                log.info("执行第【{}】次的消息生产", times.getAndIncrement());
                template.convertAndSend("UserExchange", "UserRouting", userInfo);
            }
        }.setName("User信息"), 1, 1, TimeUnit.MINUTES);
    }
}
