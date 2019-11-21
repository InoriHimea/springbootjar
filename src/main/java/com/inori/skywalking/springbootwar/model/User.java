package com.inori.skywalking.springbootwar.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String id,name,sex,classroom;
    private int age,height,weight;
}
