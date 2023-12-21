package com.github.cadedi.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Spring Cloud Gateway 的Filter分为GatewayFilter和GlobalFilter两种，二者区别如下
 *
 * GatewayFilter : 需要配置在具体路由下，只作用在当前路由上或通过spring.cloud.default-filters配置在全局，作用在所有路由上
 * GlobalFilter : 全局过滤器，作用在每个路由上。
 *
 * 以下是GlobalFilter的定义,过滤没有token头的请求
 */
public class GlobalTokenFilter implements GlobalFilter, Ordered {

    Logger logger= LoggerFactory.getLogger( GlobalTokenFilter.class );


    /**
     * 过滤逻辑
     * @param exchange 转换器--封装了来自请求中所有信息，比如：请求方法，请求参数，请求路径，请求头，cookie等
     * @param chain 过滤器链--使用责任链模式，决定当前过滤器是放行还是拒绝
     */
     @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        // String token = exchange.getRequest().getHeaders().getFirst("token");
        String token = exchange.getRequest().getQueryParams().getFirst("token");
        if (token == null || token.isEmpty()) {
            logger.info( "token is empty..." );
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
            // 创建要写入响应的数据流
            Flux<DataBuffer> dataStream = Flux.just("token lost!")
                    .map(str -> bufferFactory.wrap(str.getBytes()));
            return exchange.getResponse().writeWith(dataStream);
            // 直接中止,不输出任何信息
            // return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /**
     * 值越大则优先级越低
     */
    @Override
    public int getOrder() {
        return 0;
    }


}
