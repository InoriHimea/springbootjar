package com.inori.skywalking.springbootwar.service;

public interface ActiveMQService {

    boolean sendUser2MQ(String id, String dbType);
}
