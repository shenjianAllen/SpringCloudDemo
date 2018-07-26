package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.EnableZipkinServer;

@SpringBootApplication
@EnableZipkinServer
public class ServerzipkinApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerzipkinApplication.class, args);
	}
}
