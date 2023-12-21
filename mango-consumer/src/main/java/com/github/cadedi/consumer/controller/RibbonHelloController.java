package com.github.cadedi.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * 注入配置@LoadBalanced的RestTemplate(见启动类)
 *     @Bean
 *     @LoadBalanced   //拦截请求
 *     public RestTemplate restTemplate(){
 *         return new RestTemplate();
 *     }
 * 即可**自动调用loadBalancerClient**
 *  使用:
 *  **使用服务名(在consul注册的服务提供者)代替请求根地址**
 *
 *  ps: 事实上依赖的不是ribbon而是spring-cloud-loadBalancer
 */
@RestController
public class RibbonHelloController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/ribbon/call")
    public String call() throws InterruptedException {
        String callServiceResult = restTemplate.getForObject("http://mango-producer/hello", String.class);
        // Thread.sleep(1);
        return callServiceResult;
    }
}
