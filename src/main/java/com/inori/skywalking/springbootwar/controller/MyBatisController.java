package com.inori.skywalking.springbootwar.controller;

import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.service.MyBatisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/mybatis")
@Api("默认展示的Controller")
public class MyBatisController {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisController.class);

    @Autowired
    private MyBatisService myBatisService;

    @GetMapping("/user/{id}")
    @ResponseBody
    @ApiOperation("根据ID查询用户表，通过MybatisPlus默认的方式")
    public User getUserInfo(@PathVariable("id") String id) {
        logger.info("开始获取ID为{}的用户信息", id);
        return myBatisService.getUserInfoById(id);
    }

    @GetMapping("/{type}/list/user")
    @ResponseBody
    @ApiOperation("查询所有用户，通过Mybatis的xml的方式")
    public List<User> getUsersInfo(@PathVariable("type") String dbType) {
        logger.info("获取列表");
        return myBatisService.getUsersInfo(dbType);
    }

    @GetMapping("/user/page/{id}")
    @ApiOperation("通过ID查询用户，并通过模板页面返回，基于MybatisPlus的默认查询方式")
    public String getUserInfo2Page(@PathVariable("id") String id, Model model) {
        logger.info("查询ID为{}的信息", id);
        User user = myBatisService.getUserInfoById(id);
        model.addAttribute("user", user);
        return "/base_page.html";
    }
}
