package com.github.cadedi.gateway;


import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @RequestMapping("/fallback")
    public Mono<String> fallback(ServerWebExchange exchange) {
        // 获取错误url的响应信息
        Exception e = exchange.getAttribute(ServerWebExchangeUtils.CIRCUITBREAKER_EXECUTION_EXCEPTION_ATTR);
        ServerWebExchange delegate = exchange;
        if (exchange instanceof ServerWebExchangeDecorator)
        {
            delegate = ((ServerWebExchangeDecorator)exchange).getDelegate();
        }
        String url = delegate.getRequest().getURI().getPath();
        HttpStatus statusCode = delegate.getResponse().getStatusCode();
        return Mono.just("fallback");
    }
}
