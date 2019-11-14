package com.inori.skywalking.springbootwar.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.inori.skywalking.springbootwar.mapper.UserMapper;
import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.service.MyBatisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MyBatisServiceImpl extends ServiceImpl<UserMapper, User> implements MyBatisService {

    private static final Logger logger = LoggerFactory.getLogger(MyBatisServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserInfoById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public List<User> getUsersInfo(String type) {
        logger.info("当前的数据库类型{}", type);

        switch (type) {
            case "pgsql1":
                return getUsersFromPgSQL1();
            case "pgsql2":
                return getUsersFromPgSQL2();
            case "mysql1":
                return getUsersFromMySQL1();
            case "mysql2":
                return getUsersFromMySQL2();
            case "mysql3":
                return getUsersFromMySQL3();
        }

        return null;
    }

    @DS("mysql1")
    public List<User> getUsersFromMySQL1() {
        logger.info("从mysql1获取数据");
        return userMapper.getUserInfo(null);
    }

    @DS("mysql2")
    public List<User> getUsersFromMySQL2() {
        logger.info("从mysql2获取数据");
        return userMapper.getUserInfo(null);
    }

    @DS("mysql3")
    public List<User> getUsersFromMySQL3() {
        logger.info("从mysql2获取数据");
        return userMapper.getUserInfo(null);
    }

    @DS("postgresql1")
    public List<User> getUsersFromPgSQL1() {
        logger.info("从postgres1获取数据");
        return userMapper.getUserInfo("skywalking");
    }

    @DS("postgresql2")
    public List<User> getUsersFromPgSQL2() {
        logger.info("从postgres2获取数据");
        return userMapper.getUserInfo("skywalking");
    }
}
