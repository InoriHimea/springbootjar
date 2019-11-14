package com.inori.skywalking.springbootwar.service;

import com.inori.skywalking.springbootwar.model.User;

import java.util.List;

public interface MyBatisService {

    /**
     * 获取用户信息ById
     * @param id
     * @return
     */
    User getUserInfoById(String id);

    List<User> getUsersInfo(String type);
}
