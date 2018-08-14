package com.cloud;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

//    @StreamListener(Sink.INPUT)
//    public void process(Message<?> message) {
//        System.out.println(message.getPayload());
//        Acknowledgment acknowledgment = message.getHeaders().get(KafkaHeaders.ACKNOWLEDGMENT, Acknowledgment.class);
//        if (acknowledgment != null) {
//            System.out.println("Acknowledgment provided");
//            acknowledgment.acknowledge();
//        }
//    }
    
    public void process(Object payload) {
    }
	
}
