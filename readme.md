## Description

This project is a derivative of [mango-platform](https://gitee.com/liuge1988/mango-platform).

启动consul，-dev表示开发模式运行，另外还有-server表示服务模式运行

`consul agent -dev`

spring-cloud-starter-openfeign 2020以后版本移除了下列组件:
* ribbon(负载均衡器,替代品spring cloud loadBalancer) 和
* hystrix(服务熔断器,停止开发,替代品sentinel,resilience4j)支持

ribbon(以及api兼容的loadBalancer)实现负载均衡需要通过RestTemplate调用服务,较为不便(consul-discovery内部依赖,无需再引入)
而feign可声明式调用服务,实现接口即可(不过需额外引入openfeign)

mango-consumer集成sentinel

mango-gateway集成resilience4j(与spring-cloud-gateway集成更方便)

[sentinel](https://sentinelguard.io/zh-cn/docs/dashboard.html) 有独立的控制台,下载jar包运行即可启动控制台服务器,控制台也可以配置限流/熔断规则


## License

This project is licensed under the MIT (or Apache 2.0) license.