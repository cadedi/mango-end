package com.github.cadedi.consumer.controller;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Feign客户端接口
 */
@FeignClient("mango-producer")  //调用的目标服务
public interface MangoProducerService {

    //目标服务的目标方法的方法声明
    @RequestMapping("/hello")
    public String hello();

}
