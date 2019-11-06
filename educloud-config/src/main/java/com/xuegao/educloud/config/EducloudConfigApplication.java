package com.xuegao.educloud.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class EducloudConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducloudConfigApplication.class, args);
	}

}
