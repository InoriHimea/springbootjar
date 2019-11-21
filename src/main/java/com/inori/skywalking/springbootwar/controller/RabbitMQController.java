package com.inori.skywalking.springbootwar.controller;

import com.inori.skywalking.springbootwar.service.RabbitMQService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/mq/rabbit")
public class RabbitMQController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @GetMapping("{type}/user/{id}")
    @ResponseBody
    public boolean sendUser2Queue(@PathVariable("id") String id, @PathVariable("type") String dbType) {
        log.info("开始{}数据获取并传送", dbType);
        return rabbitMQService.sendUser2MQ(id, dbType);
    }
}
