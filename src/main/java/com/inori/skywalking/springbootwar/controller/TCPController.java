package com.inori.skywalking.springbootwar.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Slf4j
@RequestMapping("/tcp")
public class TCPController {

    @GetMapping("/user/{id}")
    @ResponseBody
    public String sendMessageAndReceive(@PathVariable String id) {
        return null;
    }
}
