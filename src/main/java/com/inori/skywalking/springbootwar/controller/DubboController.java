package com.inori.skywalking.springbootwar.controller;

import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.service.DubboService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/dubbo")
@Api(value = "/dubbo", tags = {"dubbo相关"})
public class DubboController {

    @Reference(version = "1.0", group = "springboot9002", timeout = 60000, check = false)
    private DubboService dubboService;

    @GetMapping("/{type}/list/user")
    @ResponseBody
    @ApiOperation("通过Dubbo查询#type中的用户")
    public List<User> getUserByDubbo(@PathVariable("type") String dbType) {
        log.info("开始通过Dubbo请求服务");
        return dubboService.getAllUser(dbType);
    }
}
