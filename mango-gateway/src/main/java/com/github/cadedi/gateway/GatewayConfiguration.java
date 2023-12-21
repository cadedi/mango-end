package com.github.cadedi.gateway;


import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import io.github.resilience4j.timelimiter.TimeLimiterRegistry;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    /**
     * Resilience4j配置方式:以下两种以及yaml方式都可
     * 都不配置则启用默认配置
     * 整合后即可为routes添加CircuitBreaker filter
     */

    /**
     * 集成了Resilience4j过滤器
     *
     * @param circuitRegistry 服务降级熔断配置
     * @param timeRegistry    服务慢请求配置
     * @return 熔断降级的filter工厂
     */
    @Bean
    public ReactiveResilience4JCircuitBreakerFactory circuitBreakerFactory(CircuitBreakerRegistry circuitRegistry,
                                                                           TimeLimiterRegistry timeRegistry)
    {
        ReactiveResilience4JCircuitBreakerFactory factory =
                new ReactiveResilience4JCircuitBreakerFactory(circuitRegistry, timeRegistry);
        factory.configureDefault(id ->
        {
            CircuitBreakerConfig circuitConf = circuitRegistry.getDefaultConfig();
            TimeLimiterConfig timeConf = timeRegistry.getDefaultConfig();
            //超时测试
            // timeConf = TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(1L)).build();
            // log.info("CircuitBreaker[{}]config:{}/{}", id, circuitConf, timeConf.getTimeoutDuration().getSeconds());
            Resilience4JConfigBuilder builder = new Resilience4JConfigBuilder(id);
            //此处仅构建了一个默认的熔断策略(还可以继续添加)
            return builder.circuitBreakerConfig(circuitConf).timeLimiterConfig(timeConf).build();
        });
        return factory;
    }

/*    @Bean
    public Customizer<ReactiveResilience4JCircuitBreakerFactory> defaultCustomizer() {
        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
                .circuitBreakerConfig(CircuitBreakerConfig.custom()
                        .slowCallDurationThreshold(Duration.ofMillis(200))
                        .build())
                .timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofMillis(200)).build())
                .build());
    }*/


}
