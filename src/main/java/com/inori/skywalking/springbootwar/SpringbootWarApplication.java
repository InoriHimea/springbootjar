package com.inori.skywalking.springbootwar;

import org.apache.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableDubboConfig
@MapperScan("com.inori.skywalking.springbootwar.mapper")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SpringbootWarApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWarApplication.class, args);
    }

}
