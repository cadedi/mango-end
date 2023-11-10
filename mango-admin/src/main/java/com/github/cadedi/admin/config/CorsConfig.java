package com.github.cadedi.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  //允许跨域的路径
                .allowedOrigins("*")    //允许跨域的源
                .allowedMethods("POST","GET","PUT","OPTIONS","DELETE")  //允许跨域的请求方式
                .maxAge(168000)     //预检间隔时间
                .allowedHeaders("*")    //允许任何头
                .allowCredentials(true);    //是否发送cookie
    }
}
