package com.inori.skywalking.springbootwar.mq.active.listener;

import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.util.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

@Component("ActiveUserListener")
@Slf4j
public class UserListener {

    @Autowired
    private JmsMessagingTemplate template;

    @JmsListener(destination = "UserQueue")
    @SendTo("ResultQueue")
    public String handleUserMessage(User user) {
        String message = "已成功接收到内容 -> " + JacksonUtils.toDefaultPrettyJson(user);
        log.info(message);
        return message;
    }
}
