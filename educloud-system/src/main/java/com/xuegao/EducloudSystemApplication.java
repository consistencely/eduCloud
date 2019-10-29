package com.xuegao;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.xuegao.dao")
public class EducloudSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducloudSystemApplication.class, args);
	}

}
