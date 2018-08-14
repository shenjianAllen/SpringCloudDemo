package com.cloud;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
@EnableBinding(Source.class)
public class HelloService {

	@Autowired
    RestTemplate restTemplate;

    @Autowired
    private Source source;

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

 
    public void sendMessage(String msg) {
        try {
        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        	Date data = new Date();
        	String dateString = formatter.format(data);
        	msg = dateString;
            boolean flag = source.output().send(MessageBuilder.withPayload(msg).build());
            System.out.println(flag);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
