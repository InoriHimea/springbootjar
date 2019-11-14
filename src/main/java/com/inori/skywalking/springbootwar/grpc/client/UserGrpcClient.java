package com.inori.skywalking.springbootwar.grpc.client;

import com.inori.skywalking.springbootwar.grpc.java.UserGrpc;
import com.inori.skywalking.springbootwar.grpc.java.UserOuterClass;
import com.inori.skywalking.springbootwar.model.User;
import io.grpc.Channel;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserGrpcClient {

    @GrpcClient("springboot")
    private Channel channel;

    public User getUserInfo(String id) {
        UserGrpc.UserBlockingStub stub = UserGrpc.newBlockingStub(channel);
        UserOuterClass.UserId.Builder idBuilder = UserOuterClass.UserId.newBuilder().setId(id);
        UserOuterClass.UserInfo user = stub.user(idBuilder.build());
        log.info("传送{}信息到服务", user.toString());
        return toUser(user);
    }

    private User toUser(UserOuterClass.UserInfo user) {
        User userInfo = new User();

        userInfo.setAge(user.getAge());
        userInfo.setClassroom(user.getClassroom());
        userInfo.setHeight(user.getHeight());
        userInfo.setId(user.getId());
        userInfo.setName(user.getName());
        userInfo.setSex(user.getSex());
        userInfo.setWeight(user.getWeight());
        return userInfo;
    }
}
