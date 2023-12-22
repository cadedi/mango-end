package com.github.cadedi.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 开启refresh后可通过访问客户端的post接口http://localhost:8005/actuator/refresh手动触发配置的刷新(引入bus则可以自动刷新)
 */
@RefreshScope   //运行客户端通过post方法(/actuator/refresh)触发作用域下spring-cloud-config的refresh(配置中心读取文件的改动刷新)
@RestController
public class SpringConfigController {

    @Value("${hello}")
    private String hello;

    @RequestMapping("/hello")
    public String from(){
        return this.hello;
    }
}
