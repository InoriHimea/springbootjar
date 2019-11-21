package com.inori.skywalking.springbootwar.mq.active.task;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.inori.skywalking.springbootwar.mapper.UserMapper;
import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.thread.ExecutorServiceManager;
import com.inori.skywalking.springbootwar.thread.NamedRunnable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.jms.Queue;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
/*@Component("ActiveMQTask")*/
/*@Component*/
public class UserProviderTask {

    private static final ScheduledExecutorService service = ExecutorServiceManager.getSingleScheduledExecutorService("ActicveMQ生产者");

    @Autowired
    private UserMapper mapper;

    @Autowired
    private Queue queue;

    @Autowired
    private JmsMessagingTemplate template;

    /*@PostConstruct*/
    public void sendMessage2Queue() {
        List<User> users = mapper.getUserInfo(null);
        final AtomicInteger times = new AtomicInteger(0);

        service.scheduleWithFixedDelay(new NamedRunnable() {

            @Override
            public void runAfter() throws Exception {
                log.info("开始传送第【{}】次数据", times.getAndIncrement());
                template.convertAndSend(queue, users);
            }
        }.setName("User传递"), 1, 1, TimeUnit.MINUTES);
    }
}
