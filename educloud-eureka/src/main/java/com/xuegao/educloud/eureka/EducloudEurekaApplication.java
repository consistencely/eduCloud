package com.xuegao.educloud.eureka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@Slf4j
@SpringBootApplication
@EnableEurekaServer
public class EducloudEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducloudEurekaApplication.class, args);
		log.info("EducloudEurekaApplication success !!");
	}

}
