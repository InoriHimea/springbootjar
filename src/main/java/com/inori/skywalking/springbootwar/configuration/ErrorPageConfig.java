package com.inori.skywalking.springbootwar.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
@Slf4j
public class ErrorPageConfig implements ErrorPageRegistrar {

    @Value("${spring.mvc.servlet.path}")
    private String baseServletPath;

    /**
     * Register pages as required with the given registry.
     *
     * @param registry the error page registry
     */
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        log.info("开始注册错误页面");
        ErrorPage page404 = new ErrorPage(HttpStatus.NOT_FOUND, baseServletPath + "error/404.html");
        ErrorPage page500 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, baseServletPath + "springboot/error/500.html");
        registry.addErrorPages(page404, page500);
        log.info("注册完成");
    }
}
