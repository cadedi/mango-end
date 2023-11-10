package com.github.cadedi.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "hello")
@RestController
public class TestController {


    @ApiOperation(value = "测试页面")
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

}
