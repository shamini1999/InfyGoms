package com.infy.infygo.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class InfygoConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(InfygoConfigServerApplication.class, args);
	}

}
