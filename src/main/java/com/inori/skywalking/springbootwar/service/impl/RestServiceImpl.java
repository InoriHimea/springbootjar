package com.inori.skywalking.springbootwar.service.impl;

import com.inori.skywalking.springbootwar.model.User;
import com.inori.skywalking.springbootwar.service.RestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class RestServiceImpl implements RestService {

    @Autowired
    private RestTemplate rest;

    @Override
    public User getUserInfoById(String id) {
        log.info("通过rest方式调用查询用户");
        return rest.getForObject("http://10.10.20.198:9000/springboot/home/user/{1}", User.class, id);
    }
}
