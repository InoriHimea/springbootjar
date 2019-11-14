package com.inori.skywalking.springbootwar.grpc.server;

import com.inori.skywalking.springbootwar.grpc.java.UserGrpc;
import com.inori.skywalking.springbootwar.grpc.java.UserOuterClass;
import com.inori.skywalking.springbootwar.mapper.UserMapper;
import com.inori.skywalking.springbootwar.model.User;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@GrpcService
@Service
public class UserGrpcServer extends UserGrpc.UserImplBase {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void user(UserOuterClass.UserId request, StreamObserver<UserOuterClass.UserInfo> responseObserver) {
        User user = userMapper.selectById(request.getId());

        final UserOuterClass.UserInfo.Builder userBuilder = UserOuterClass.UserInfo.newBuilder()
                .setId(user.getId())
                .setAge(user.getAge())
                .setClassroom(user.getClassroom())
                .setHeight(user.getHeight())
                .setName(user.getName())
                .setSex(user.getSex())
                .setWeight(user.getWeight());
        responseObserver.onNext(userBuilder.build());
        responseObserver.onCompleted();

        log.info("内容：{}", user.toString());
    }
}
