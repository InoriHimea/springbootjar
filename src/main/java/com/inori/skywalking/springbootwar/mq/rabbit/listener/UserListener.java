package com.inori.skywalking.springbootwar.mq.rabbit.listener;

import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component("RabbitUserListener")
@Slf4j
@RabbitListener(queues = "UserQueue")
public class UserListener {

    private static final AtomicInteger times = new AtomicInteger(0);

    @RabbitHandler
    @SendTo("ResultQueue")
    public String processMessage(List<User> users) {
        String result = "第【{1}】次获取到的消息 -> {2}".replace("{1}", times.getAndIncrement() + "")
                .replace("{2}", JacksonUtils.toDefaultPrettyJson(users));
        log.info("{}", result);
        return result;
    }
}
