package com.inori.skywalking.springbootwar.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.LinkedList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(ApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.inori.skywalking.springbootwar.controller"))
/*                .apis(RequestHandlerSelectors.withClassAnnotation(Controller.class))
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))*/
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(new LinkedList<>());
    }

    private ApiInfo ApiInfo() {
        return new ApiInfoBuilder()
                .title("SkyWalkingAppDemo Restful API")
                .description("一个应用集群的Demo")
                .termsOfServiceUrl("http://10.10.20.198:8080")
                .version("1.0")
                .build();
    }

}
