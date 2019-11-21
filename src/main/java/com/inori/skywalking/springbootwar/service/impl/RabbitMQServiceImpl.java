package com.inori.skywalking.springbootwar.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.inori.skywalking.springbootwar.mapper.UserMapper;
import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.service.RabbitMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RabbitMQServiceImpl implements RabbitMQService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private RabbitTemplate template;

    @Override
    @DS("#dbType")
    public boolean sendUser2MQ(String id, String dbType) {
        log.info("查询{}的id为【{}】的用户发入mq", dbType, id);
        boolean flag = false;

        try {
            List<User> users = mapper.getUserInfo(dbType.contains("pgsql") ? "skywalking" : null);
            template.convertAndSend("UserExchange", "UserRouting", users);
            flag =  true;
            log.info("消息 -> {}", template.receiveAndConvert("ResultQueue"));
        } catch (MessagingException e) {
            log.error("发送异常", e);
        }

        return flag;
    }
}
