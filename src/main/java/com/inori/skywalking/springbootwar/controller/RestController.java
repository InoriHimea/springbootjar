package com.inori.skywalking.springbootwar.controller;

import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/rest")
@Slf4j
public class RestController {

    @Autowired
    private RestService restService;

    @GetMapping("/user/{id}")
    @ResponseBody
    public User getUserInfoById(@PathVariable String id) {
        log.info("当前查询的ID是{}", id);
        return restService.getUserInfoById(id);
    }
}
