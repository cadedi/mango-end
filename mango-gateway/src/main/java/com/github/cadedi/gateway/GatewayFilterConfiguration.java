package com.github.cadedi.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayFilterConfiguration {
    @Bean
    public GlobalTokenFilter tokenFilter(){
        return new GlobalTokenFilter();
    }

    /**
     * 在apply中返回定义的局部过滤器即可为指定route配置该无参filter
     *
     * 相近的配置方式是定义一个FilterFactory组件类,效果相同,**有参filter需要有参构造器**,所以适用于类的配置,不适用匿名类对象
     * ```
     * @Component
     * public class RequestTimeGatewayFilterFactory extends AbstractGatewayFilterFactory<Object>{
     *
     * }
     * ```
     * 如果不手动重写name方法则必须按照xxxxGatewayFilterFactory的命名方式才可使yaml中filter值被正确识别
     * @return
     */
    @Bean
    public AbstractGatewayFilterFactory<Object> requestTimeGatewayFilterFactory(){
        return new AbstractGatewayFilterFactory<Object>() {

            @Override
            public GatewayFilter apply(Object config) {
                return new RequestTimeFilter();
            }

            @Override
            public String name() {
                return "RequestTime";
            }
        };
    }
}
