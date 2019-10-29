package com.xuegao.educloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class EducloudGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(EducloudGatewayApplication.class, args);
	}

}
