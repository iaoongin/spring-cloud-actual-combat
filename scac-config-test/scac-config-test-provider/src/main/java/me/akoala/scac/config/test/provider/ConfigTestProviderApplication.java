package me.akoala.scac.config.test.provider;

import me.akoala.scac.common.api.R;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
public class ConfigTestProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigTestProviderApplication.class, args);
    }

    @RestController
    public class HelloCtrl {


        @GetMapping("/hello")
        public R<String> hello() {
            return R.success(String.format("hello. now is %s", LocalDateTime.now()));
        }
    }

}
