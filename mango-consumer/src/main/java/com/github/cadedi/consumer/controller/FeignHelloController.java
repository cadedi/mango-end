package com.github.cadedi.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignHelloController {

    @Autowired
    private MangoProducerService mangoProducerService;

    @RequestMapping("/feign/call")
    public String call(){
        //调用本地接口方法,实际是feign分配的远程目标服务
        return mangoProducerService.hello();
    }
}
