package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

@SpringBootApplication
//用于开启向注册中心注册的注解
@EnableEurekaClient
//用于开启向注册中心注册的注解
@EnableDiscoveryClient
//熔断注解开启
@EnableHystrix
//开启断路器监控图形化界面
@EnableHystrixDashboard
//开启断路器
@EnableCircuitBreaker
//********************************EnableHystrixDashboard与EnableCircuitBreaker这两个注解（监控）会导致熔断失败
//重试注解开启
@EnableRetry
public class ServiceribbonApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceribbonApplication.class, args);
	}
	
	//将RestTemplate注入到该系统中，待其他业务@Autowired
    @Bean
    //负载均衡注解(发起远程调用的模板类)
    @LoadBalanced
    RestTemplate restTemplate() {
    	//ribbon直接用yml（ribbon.`````）配置超时是无效的，需要用以下代码实现
        HttpComponentsClientHttpRequestFactory httpRequestFactory =  new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(3000);
        httpRequestFactory.setConnectTimeout(1000);
        return new RestTemplate(httpRequestFactory);
    }
    
    
    /**
     * 使用代码注册Servlet（不需要@ServletComponentScan注解）
     *
     * 用于图形化监控使用
     * @author shenjian
     * @create  2018-7-24 14:16:10
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
    	HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
}
