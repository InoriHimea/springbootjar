package com.inori.skywalking.springbootwar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.inori.skywalking.springbootwar.mapper")
public class SpringbootWarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWarApplication.class, args);
    }

}
