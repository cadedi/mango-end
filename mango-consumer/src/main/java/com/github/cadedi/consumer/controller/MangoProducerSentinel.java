package com.github.cadedi.consumer.controller;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 服务提供者不可用时使用的fallback方法
 */
@Component
public class MangoProducerSentinel implements MangoProducerService{


    @RequestMapping("/hello")
    @Override
    public String hello() {
        return "sorry, hello service call failed. ";
    }
}
