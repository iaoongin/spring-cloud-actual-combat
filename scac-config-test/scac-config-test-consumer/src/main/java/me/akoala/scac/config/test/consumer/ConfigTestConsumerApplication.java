package me.akoala.scac.config.test.consumer;

import me.akoala.scac.common.api.R;
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
public class ConfigTestConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigTestConsumerApplication.class, args);
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
        public R<R> hello() {
            R<String> remoteRes = restTemplate.getForEntity("http://CONFIG-TEST-PROVIDER/hello", R.class).getBody();
            return R.success(remoteRes);
        }
    }

}
