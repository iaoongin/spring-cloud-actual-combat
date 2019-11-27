package me.akoala.scac.eureka.test.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

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

}
