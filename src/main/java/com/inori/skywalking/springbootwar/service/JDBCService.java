package com.inori.skywalking.springbootwar.service;

import com.inori.skywalking.springbootwar.model.User;

import java.util.List;

public interface JDBCService {

    User getUserById(String id);

    List<User> getUsersInfo();
}
