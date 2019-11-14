package com.inori.skywalking.springbootwar.controller;

import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.service.JDBCService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/jdbc")
@Api("JDBC方式")
public class JDBCController {

    @Autowired
    private JDBCService jdbcService;

    @GetMapping("/user/{id}")
    @ResponseBody
    public User getUserById(@PathVariable("id") String id) {
        log.info("通过JDBC方式查询用户id={}的信息", id);
        return jdbcService.getUserById(id);
    }

    @GetMapping("/list/user")
    @ResponseBody
    public List<User> getUsersInfo() {
        log.info("通过JDBC获取列表");
        return jdbcService.getUsersInfo();
    }
}
