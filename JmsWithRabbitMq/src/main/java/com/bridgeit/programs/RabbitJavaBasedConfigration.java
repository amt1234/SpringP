package com.bridgeit.programs;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;

@Configuration
@ComponentScan(basePackages="com.bridgeit.programs")
public class RabbitJavaBasedConfigration {

	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMessageListenerContainer.class);
	static final String topicExchangeName="Spring example";
	
	static final String QueueName="EmailQueue";
	
	public RabbitJavaBasedConfigration() {
		System.out.println("Working");
	}
	
	@Bean
	Queue queue() {
		return new Queue(QueueName,false);
	}
	
	@Bean
	TopicExchange topicExchange() {
		return new TopicExchange(topicExchangeName);
	}
	
	@Bean
	Binding binding(Queue queue,TopicExchange topicExchange) {
		return BindingBuilder.bind(queue).to(topicExchange).with("lazy.orange.#");
	}
	
	
//	@Bean
//	SimpleMessageListenerContainer container(ConnectionFactory connectionFactory,MessageListener messageListener) {
//		SimpleMessageListenerContainer simpleMessageListenerContainer=new SimpleMessageListenerContainer();
//		simpleMessageListenerContainer.setConnectionFactory(connectionFactory);
//		simpleMessageListenerContainer.setQueueNames(QueueName);
//		simpleMessageListenerContainer.setMessageListener(messageListener);
//		
//		return simpleMessageListenerContainer;		
//	}
	@Bean
    public SimpleMessageListenerContainer messageListenerContainer() {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(rabbitConnectionFactory());
        container.setQueueNames(QueueName);
        container.setMessageListener(exampleListener());
        return container;
    }
	
	 @Bean
	    public ConnectionFactory rabbitConnectionFactory() {
	        CachingConnectionFactory connectionFactory =
	            new CachingConnectionFactory("localhost");
	        connectionFactory.setUsername("guest");
	        connectionFactory.setPassword("guest");
	        return connectionFactory;
	    }

	    @Bean
	    public MessageListener exampleListener() {
	        return new MessageListener() {
	            public void onMessage(Message message) {
	            	System.out.println(new String(message.getBody(), StandardCharsets.UTF_8));
	            	Receiver.receiverMessageDemo(new String(message.getBody(), StandardCharsets.UTF_8));
	            }

	        };
	    }
	
//	@Bean
//	MessageListenerAdapter messageListenerAdapter(Receiver receiver) {
//		return new MessageListenerAdapter(receiver,"receiverMessageDemo");
//	}
	
	
	@Bean
	AmqpAdmin amqpAdmin() {
		AmqpAdmin amqpAdmin = new RabbitAdmin(rabbitConnectionFactory());
		amqpAdmin.declareQueue(queue());
		amqpAdmin.declareExchange(topicExchange());
		amqpAdmin.declareBinding(binding(queue(), topicExchange()));
		return amqpAdmin;
	}
//	
//	@Bean
//	ConnectionFactory connectionFactory() {
//		CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        connectionFactory.setHost("localhost");
//        connectionFactory.setPort(5672);
//        connectionFactory.setUsername("");
//        connectionFactory.setPassword("");
//        connectionFactory.setVirtualHost(virtual_host);
//        return connectionFactory;
//	}
//	
	@Bean
	RabbitTemplate rabbitTemplate()
	{
		RabbitTemplate rabbitTemplate=new RabbitTemplate(rabbitConnectionFactory());
		RetryTemplate retryTemplate = new RetryTemplate();
	    ExponentialBackOffPolicy backOffPolicy = new ExponentialBackOffPolicy();
	    backOffPolicy.setInitialInterval(500);
	    backOffPolicy.setMultiplier(10.0);
	    backOffPolicy.setMaxInterval(10000);
	    retryTemplate.setBackOffPolicy(backOffPolicy);
	    rabbitTemplate.setRetryTemplate(retryTemplate);
		return rabbitTemplate;
	}
	
}
