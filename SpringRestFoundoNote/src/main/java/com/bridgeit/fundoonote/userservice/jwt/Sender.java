package com.bridgeit.fundoonote.userservice.jwt;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.bridgeit.fundoonote.userservice.configration.FundooNoteConfigration;

@Component
public class Sender {
	RabbitTemplate rabbitTemplate;

	public Sender(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}
	
	public void runMethod() {
		System.out.println("sending messages...");
		//rabbitTemplate.convertAndSend(FundooNoteConfigration.topicExchangeName,"lazy.orange.rabbit","hello from rabbitMq");
		rabbitTemplate.convertAndSend(FundooNoteConfigration.topicExchangeName,"lazy.orange.rabbit","hello from rabbitMQ");
	}
}
