package com.github.cadedi.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableDiscoveryClient
@EnableConfigServer //开启配置服务支持
@SpringBootApplication
public class MangoConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoConfigApplication.class, args);
    }

}
