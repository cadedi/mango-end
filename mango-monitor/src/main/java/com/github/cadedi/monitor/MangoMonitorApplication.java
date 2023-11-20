package com.github.cadedi.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAdminServer  //开启监控服务器,其它应用向当前服务(localhost:8000)注册客户端即可被监控,访问http://localhost:8000/查看客户端的监控信息
@SpringBootApplication
public class MangoMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoMonitorApplication.class, args);
    }

}
