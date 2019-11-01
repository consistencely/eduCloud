package com.xuegao.educloud.user.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.xuegao.educloud.system.client")
public class EducloudUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducloudUserApplication.class, args);
	}

}
