package com.inori.skywalking.springbootwar.service;

import com.inori.skywalking.springbootwar.model.User;

import java.util.List;

public interface DubboService {

    List<User> getAllUser(String dbType);
}
