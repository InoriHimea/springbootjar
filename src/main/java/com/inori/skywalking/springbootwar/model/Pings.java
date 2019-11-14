package com.inori.skywalking.springbootwar.model;

import lombok.Data;

@Data
public class Pings {

    private String ip;
    private int bytes;
    private int time;
    private int ttl;
}
