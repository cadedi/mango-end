package com.github.cadedi.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@EnableDiscoveryClient //开启服务发现支持,访问http://localhost:8500/查看是否成功注册到consul
//默认扫描启动类所在包及子包时可不显式配置
@SpringBootApplication(scanBasePackages = {"com.github.cadedi.admin"})
public class MangoAdminApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MangoAdminApplication.class, args);
        // SysConfigMapper sysConfigMapper = (SysConfigMapper)context.getBean("sysConfigMapper");
        // SysConfig sysConfig = sysConfigMapper.selectByPrimaryKey(1L);
        // System.out.println(sysConfig);
    }

}
