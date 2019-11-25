package com.xuegao.educloud.gateway;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@Slf4j
@SpringBootApplication
@EnableZuulProxy
public class EducloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducloudGatewayApplication.class, args);
		log.info("EducloudGatewayApplication success !!");
	}

}
