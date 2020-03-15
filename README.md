# spring cloud actual combat (scac)
spring cloud 实战项目。

## 项目结构
案例1 spring cloud eureka 单体
- scac-eureka 单体eureka 注册中心服务 


案例2 spring cloud eureka 集群
- scac-eureka-peer 集群eureka 注册中心服务 
- scac-eureka-test eureka 注册中心服务 测试 
    - scac-eureka-test-provider 服务提供者
    - scac-eureka-test-consumer 服务消费者
    
案例3 spring cloud config
- scac-config-server config 服务
- scac-config-eureka-peer config客户端，使用config服务的 集群eureka 注册中心服务，即 

## 本地hosts配置
```
127.0.0.1 peer1.eureka.scac
127.0.0.1 peer2.eureka.scac
127.0.0.1 peer3.eureka.scac
127.0.0.1 single.config.scac
```

## 启动配置
### 启动类
```
class EurekaPeerApplication
```
### 各实例启动参数
```
peer1 args --spring.profiles.active=peer1
peer2 args --spring.profiles.active=peer2
peer3 args --spring.profiles.active=peer3
```

## 运行效果
访问: http://peer1.eureka.scac:1111/dashboard

![UTOOLS1574849206703.png](https://i.loli.net/2019/11/27/6ORGEIX48n513HQ.png)