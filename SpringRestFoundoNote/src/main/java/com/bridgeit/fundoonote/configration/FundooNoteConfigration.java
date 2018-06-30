package com.bridgeit.fundoonote.configration;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.retry.backoff.ExponentialBackOffPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.bridgeit.fundoonote.jwt.Receiver;
import com.bridgeit.fundoonote.model.User;

@Configuration
@ComponentScan(basePackages="com.bridgeit.fundoonote")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class FundooNoteConfigration {
	private static final Logger LOGGER = LoggerFactory.getLogger(SimpleMessageListenerContainer.class);

	public static final String topicExchangeName="Spring example";
	
	static final String QueueName="EmailQueue";
	
	@Autowired
	Environment environment;
	
	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
		messageConverters.add(mappingJackson2HttpMessageConverter());
		requestMappingHandlerAdapter.setMessageConverters(messageConverters);
		return requestMappingHandlerAdapter;
	}

	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		return new MappingJackson2HttpMessageConverter();
	}
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.
		setUrl("jdbc:mysql://localhost:3306/fundoonotes");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("root");

		return driverManagerDataSource;
	}
	@Bean
	public LocalSessionFactoryBean getSessionFactory() throws IOException {

		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setPackagesToScan("com.bridgeit.fundoonote");
		
		Properties properties = new Properties();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql",environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.hbm2ddl.auto",environment.getRequiredProperty("hibernate.hbm2ddl.auto"));

		/*properties.put("mysql_driver",environment.getRequiredProperty("mysql.driver"));
		properties.put("mysql_url",environment.getRequiredProperty("mysql.url"));
		properties.put("mysql_username",environment.getRequiredProperty("mysql.username"));
		properties.put("mysql_password",environment.getRequiredProperty("mysql.password"));*/
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setHibernateProperties(properties);
		
		sessionFactoryBean.setAnnotatedClasses(User.class);
		return sessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() throws IOException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory().getObject());

		return transactionManager;
	}

	//RabbitMQ configration
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
	    
	    @Bean
		AmqpAdmin amqpAdmin() {
			AmqpAdmin amqpAdmin = new RabbitAdmin(rabbitConnectionFactory());
			amqpAdmin.declareQueue(queue());
			amqpAdmin.declareExchange(topicExchange());
			amqpAdmin.declareBinding(binding(queue(), topicExchange()));
			return amqpAdmin;
		}
	    
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
