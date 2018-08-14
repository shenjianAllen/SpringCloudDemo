package com.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;



@EnableBinding(Sink.class)
public class HelloControler {

	@Autowired
	HelloService helloService;

    @StreamListener(Sink.INPUT)
    public void recivekafka(String payload) {
    	System.out.println(payload);
//    	helloService.process(payload);
    }
    
}
