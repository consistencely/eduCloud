package com.xuegao.educloud.user.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringCloudApplication
@EnableFeignClients(basePackages = "com.xuegao.educloud.system.client")
@MapperScan("com.xuegao.educloud.user.server.dao")
@ComponentScan(basePackages = "com.xuegao.educloud") //扫描引入jar包的注解
public class EducloudUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducloudUserApplication.class, args);
	}

}
