package com.infy.infygo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class InfygoEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfygoEurekaApplication.class, args);
	}

}
