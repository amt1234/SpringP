package com.bridgeit.programs;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class Runner  {
	
	RabbitTemplate rabbitTemplate;

	public Runner(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	public void runMethod() {
		System.out.println("sending messages...");
		rabbitTemplate.convertAndSend(RabbitJavaBasedConfigration.topicExchangeName,"lazy.orange.rabbit","hello from rabbitMq");
		
	}
}
