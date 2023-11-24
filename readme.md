
#启动consul，-dev表示开发模式运行，另外还有-server表示服务模式运行
consul agent -dev

spring-cloud-starter-openfeign 2020以后版本移除了ribbon(负载均衡器,替代品spring cloud loadBalancer) 和hystrix(服务熔断器,替代品sentinel)支持
替代品