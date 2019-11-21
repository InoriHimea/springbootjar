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
    @DS("#type")
    public List<User> getUsersInfo(String type) {
        logger.info("当前的数据库类型{}", type);
        return userMapper.getUserInfo(type.contains("pgsql") ? "skywalking" : null);
    }
}
