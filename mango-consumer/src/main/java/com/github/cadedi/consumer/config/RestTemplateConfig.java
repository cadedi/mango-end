package com.github.cadedi.consumer.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 通过拦截 restTemplate 实现负载均衡
 */
@Configuration
// 指定只要是 mango-producer 服务，都会使用我们指定的策略 LoadBalancerConfig
@LoadBalancerClient(value = "mango-producer", configuration = LoadBalancerConfig.class)
public class RestTemplateConfig {

    @Bean
    @LoadBalanced   //拦截请求
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
