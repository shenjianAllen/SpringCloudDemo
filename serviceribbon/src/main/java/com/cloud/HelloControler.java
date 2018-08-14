package com.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {

	
    @Autowired
    HelloService helloService;
    

    @GetMapping(value = "/hi")
    public String hi(@RequestParam String name) {
        return helloService.hiService( name );
    }
    
    @GetMapping(value = "/sendkafka")
    public void sendkafka(@RequestParam String msg) {
    	helloService.sendMessage(msg);
    }
    
}
