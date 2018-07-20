package com.e3expo.e3.rabbitmq.listener;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@EnableRabbit
public class HelloWorldListener {
	
	
	@RabbitListener(queues="queue-test")
	public void helloWorld(String helloString) {
		
		System.out.println("This is a listner Hello:" + helloString);
		
	}
	
	

}

