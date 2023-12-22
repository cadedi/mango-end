package com.github.cadedi.consumer.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringConfigController {

    @Value("${hello}")
    private String hello;

    @RequestMapping("/hello")
    public String from(){
        return this.hello;
    }
}
