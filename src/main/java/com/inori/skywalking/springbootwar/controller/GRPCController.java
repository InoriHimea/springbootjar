package com.inori.skywalking.springbootwar.controller;

import com.inori.skywalking.springbootwar.grpc.client.UserGrpcClient;
import com.inori.skywalking.springbootwar.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/grpc")
public class GRPCController {

    @Autowired
    private UserGrpcClient client;

    @GetMapping("/user/{id}")
    public User getUserInfo(@PathVariable("id") String id) {
        return client.getUserInfo(id);
    }
}
