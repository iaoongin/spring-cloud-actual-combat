# spring cloud actual combat (scac)
spring cloud 实战项目。

## 项目结构
- scac-eureka 单体eureka 注册中心服务 
- scac-eureka-peer 集群eureka 注册中心服务 
- scac-eureka-test eureka 注册中心服务 测试 
    - scac-eureka-test-provider 服务提供者
    - scac-eureka-test-consumer 服务消费者
    
## 本地hosts配置
```
127.0.0.1 peer1.eureka.scac
127.0.0.1 peer2.eureka.scac
127.0.0.1 peer3.eureka.scac
```

## 运行效果
访问: http://peer1.eureka.scac:1111/dashboard

![UTOOLS1574849206703.png](https://i.loli.net/2019/11/27/6ORGEIX48n513HQ.png)