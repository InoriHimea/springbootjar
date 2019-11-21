package com.inori.skywalking.springbootwar.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.inori.skywalking.springbootwar.mapper.UserMapper;
import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.service.DubboService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0", group = "springboot9002", timeout = 60000)
@Slf4j
public class DubboServiceImpl implements DubboService {

    @Autowired
    private UserMapper mapper;

    @Override
    @DS("#dbType")
    public List<User> getAllUser(String dbType) {
        return mapper.getUserInfo(dbType.contains("postgresql") ? "skywalking" : null);
    }
}
