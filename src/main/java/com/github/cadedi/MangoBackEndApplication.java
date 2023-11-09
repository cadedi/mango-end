package com.github.cadedi;

import com.github.cadedi.dao.SysConfigMapper;
import com.github.cadedi.model.SysConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;


//默认扫描启动类所在包及子包时可不显式配置
@SpringBootApplication(scanBasePackages = {"com.github.cadedi"})
public class MangoBackEndApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(MangoBackEndApplication.class, args);
        // SysConfigMapper sysConfigMapper = (SysConfigMapper)context.getBean("sysConfigMapper");
        // SysConfig sysConfig = sysConfigMapper.selectByPrimaryKey(1L);
        // System.out.println(sysConfig);
    }

}
