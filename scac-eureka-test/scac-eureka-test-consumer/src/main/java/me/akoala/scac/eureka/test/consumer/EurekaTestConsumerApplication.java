package me.akoala.scac.eureka.test.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author xiaohongxin
 * @version 1.0.0
 * @date 2019/11/27 15:14
 */
@EnableEurekaClient
@SpringBootApplication
public class EurekaTestConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaTestConsumerApplication.class, args);
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @RestController
    public class HelloCtrl {

        @Resource
        private RestTemplate restTemplate;


        @GetMapping("/hello")
        public String hello() {

            String remoteRes = restTemplate.getForEntity("http://EUREKA-TEST-PROVIDER/hello", String.class).getBody();

            return String.format("remote. [%s]", remoteRes);
        }
    }

}
