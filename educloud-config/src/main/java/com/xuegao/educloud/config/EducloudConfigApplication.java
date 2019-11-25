package com.xuegao.educloud.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@Slf4j
@SpringBootApplication
@EnableConfigServer
public class EducloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducloudConfigApplication.class, args);
		log.info("EducloudConfigApplication success !!");
	}

}
