package com.xuegao.educloud.system.server;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
@MapperScan("com.xuegao.educloud.system.server.dao")
public class EducloudSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducloudSystemApplication.class, args);
		log.info("EducloudSystemApplication success !!");
	}

}
