package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaserverNodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaserverNodeApplication.class, args);
	}
}
