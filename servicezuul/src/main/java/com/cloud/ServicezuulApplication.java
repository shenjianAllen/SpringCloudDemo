package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
//开启zuul
@EnableZuulProxy
//用于开启向注册中心注册的注解
@EnableEurekaClient
//用于开启向注册中心注册的注解
@EnableDiscoveryClient
public class ServicezuulApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicezuulApplication.class, args);
	}
}
