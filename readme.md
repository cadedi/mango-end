## Description

This project is a derivative of [mango-platform](https://gitee.com/liuge1988/mango-platform).

* Consul: 注册中心,模块可向consul注册

    启动consul，-dev表示开发模式运行，另外还有-server表示服务模式运行

`consul agent -dev`

* spring-cloud-starter-openfeign 2020以后版本移除了下列组件:
  * ribbon(负载均衡器,替代品spring cloud loadBalancer) 和
  * hystrix(服务熔断器,停止开发,替代品sentinel,resilience4j)支持


* 对于负载均衡器的实现
  * ribbon(以及api兼容的loadBalancer)实现负载均衡需要通过RestTemplate调用服务,较为不便(consul-discovery-starter内部依赖,无需再引入)
  * feign可声明式调用服务,实现接口即可(不过需额外引入openfeign)

* 对于熔断器的集成选择 
  * mango-consumer(后端服务demo)集成sentinel 
  * mango-gateway集成resilience4j(与spring-cloud-gateway集成更方便)
  * [sentinel](https://sentinelguard.io/zh-cn/docs/dashboard.html) 有独立的控制台,下载jar包运行即可启动控制台服务器,控制台也可以配置限流/熔断规则

* 链路追踪 -定位前端请求产生的后端服务调用链中的故障点,收集各服务的时间数据
**ZipKin(推特开源) + spring cloud sleuth**

  > 前置:下载elasticsearch+elasticsearch-head-master(node < 9.0.0),实际推荐ZipKin与下载elasticsearch通过docker部署,以下是本地部署流程

  下载 [ZipKin](https://zipkin.io/pages/quickstart.html) jar包,elasticsearch作为存储方式(mysql较慢,内存不适合生产环境), 通过环境变量配置elasticsearch数据库地址,并启动
  ```bash
  STORAGE_TYPE=elasticsearch 
  ES_HOSTS=http://127.0.0.1:9200 
  java -jar zipkin.jar
  ```
  除了zipkin客户端,还需要引入依赖(链路上所有节点)
  ```xml
  <!--zipkin + sleuth-->
  <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-sleuth-zipkin</artifactId>
  </dependency>
  <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-sleuth</artifactId>
  </dependency>
  ```
  所有链路上的模块节点配置上zipkin
  ```yaml
  spring:
    zipkin:
      base-url: http://localhost:9411/
    sleuth:
      sampler:
        probability: 1 #样本采集率,默认为0.1,即10%
  ```
  依次启动注册中心,生产者,消费者.....,当发生服务调用时,可以在[zipkin ui](http://localhost:9411/) 查看调用链上每个节点(引入了sleuth的)耗时
  ![](https://raw.githubusercontent.com/cadedi/picto/main/img/202312220855811.png)

## License

This project is licensed under the MIT (or Apache 2.0) license.