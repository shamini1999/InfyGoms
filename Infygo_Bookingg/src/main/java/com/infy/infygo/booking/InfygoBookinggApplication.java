package com.infy.infygo.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class InfygoBookinggApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfygoBookinggApplication.class, args);
	}

}
