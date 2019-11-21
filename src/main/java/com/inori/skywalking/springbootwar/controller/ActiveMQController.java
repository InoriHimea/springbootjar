package com.inori.skywalking.springbootwar.controller;

import com.inori.skywalking.springbootwar.service.ActiveMQService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/mq/active")
@Api(value = "/mq/active", tags = {"ActiveMQ相关"})
public class ActiveMQController {

    @Autowired
    private ActiveMQService activeMQService;

    @GetMapping("/{type}/user/{id}")
    @ResponseBody
    @ApiOperation(value = "/{type}/user/{id}", tags = {"发送用户"}, response = Boolean.class)
    public boolean sendUser2MQ(@PathVariable("id") String id, @PathVariable("type") String dbType) {
        log.info("开始{}数据获取并传送！", dbType);
        return activeMQService.sendUser2MQ(id, dbType);
    }
}
