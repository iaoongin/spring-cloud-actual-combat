package me.akoala.scac.config.eureka.peer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * <p>
 * TODO
 * </p>
 *
 * @author xiaohongxin
 * @version 1.0.0
 * @date 2019/11/27 15:14
 */
@EnableEurekaServer
@SpringBootApplication
public class ConfigEurekaPeerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigEurekaPeerApplication.class, args);
    }

}
