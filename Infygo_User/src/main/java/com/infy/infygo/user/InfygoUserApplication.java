package com.infy.infygo.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InfygoUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfygoUserApplication.class, args);
	}

}
