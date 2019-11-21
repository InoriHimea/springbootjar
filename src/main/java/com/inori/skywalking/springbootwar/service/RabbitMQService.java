package com.inori.skywalking.springbootwar.service;

public interface RabbitMQService {

    boolean sendUser2MQ(String id, String dbType);
}
