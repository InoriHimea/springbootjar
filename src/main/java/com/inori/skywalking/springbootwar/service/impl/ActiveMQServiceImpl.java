package com.inori.skywalking.springbootwar.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.inori.skywalking.springbootwar.mapper.UserMapper;
import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.service.ActiveMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.util.List;

@Service
@Slf4j
public class ActiveMQServiceImpl implements ActiveMQService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private Queue queue;

    @Autowired
    private JmsMessagingTemplate template;

    @Override
    @DS("#dbType")
    public boolean sendUser2MQ(String id, String dbType) {
        log.info("查询{}的id为【{}】的用户发入mq", dbType, id);
        boolean flag = false;

        try {
            List<User> users = mapper.getUserInfo(dbType.contains("pgsql") ? "skywalking" : null);
            template.convertAndSend(queue, users);
            flag =  true;
            log.info("结果 => {}", template.receiveAndConvert("ResultQueue", String.class));
        } catch (MessagingException e) {
            log.error("发送异常", e);
        }

        return flag;
    }
}
