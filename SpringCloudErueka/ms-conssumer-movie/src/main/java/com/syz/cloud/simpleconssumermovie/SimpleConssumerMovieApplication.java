package com.syz.cloud.simpleconssumermovie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
public class SimpleConssumerMovieApplication {

    /**
     * Description:启东时初识化RestTemplate
     * Created by syz on 2017/8/10 0010 20:49
     */
    @Bean   //作用就是实例化new RestTemplate()对象，已方这个法名为实例化名称的注解  就是实例化为restTemplate
    //相当于RestTemplate restTemplate = new RestTemplate();
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * Description:spring-boot启动方法  微服务启动入口
     * Created by syz on 2017/8/10 0010 20:53
     */
    public static void main(String[] args) {
        SpringApplication.run(SimpleConssumerMovieApplication.class, args);
    }
}
