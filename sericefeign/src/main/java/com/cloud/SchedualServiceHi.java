package com.cloud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

//Feign内部实现了负载均衡，即有了熔断的机制：	fallback定义的实现类需要自己去实现，此定义即为熔断后的通信方法
@FeignClient(value = "eurka-client", fallback = SchedualServiceHiHystricImpl.class)
public interface SchedualServiceHi {
	
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);

}
