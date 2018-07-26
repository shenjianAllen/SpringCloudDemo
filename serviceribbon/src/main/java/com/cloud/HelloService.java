package com.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HelloService {

	@Autowired
    RestTemplate restTemplate;

//    public String hiService(String name) {
//        return restTemplate.getForObject("http://eurka-client/hi?name="+name,String.class);
//    }
    
	//熔断注解：用于执行接口不通或者服务垮掉，
    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://eurka-client/hi?name="+name,String.class);
    }
    
    public String hiError(String name) {
        return "hi,"+name+",sorry,error!";
    }
	
}
