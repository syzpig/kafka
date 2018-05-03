package com.syz.cloud.simpleprovideruser;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 *Description:这个类要放在包最外层 ，因为他是spring-boot启动类，注解对该文件所在包以及自路径下有作用
 *Created by syz on 2017/8/9 0009 22:18
 */
@SpringBootApplication
@EnableEurekaClient  // 注册第二步，添加eureka注解  这个注解仅对eureka适用，可以使用@EnableDiscoveryClient
                     //EnableEurekaClient其实和他是等价的，他是对EnableDiscoveryClient基础上更加严密的，
public class SimpleProviderUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleProviderUserApplication.class, args);
	}
}
