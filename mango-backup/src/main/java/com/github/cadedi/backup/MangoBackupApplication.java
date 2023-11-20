package com.github.cadedi.backup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 独立于主系统运行
 */
@SpringBootApplication(scanBasePackages = {"com.github.cadedi"})
public class MangoBackupApplication {

    public static void main(String[] args) {
        SpringApplication.run(MangoBackupApplication.class,args);
        System.out.println(System.getProperty("user.home"));
    }
}
