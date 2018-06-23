package com.bridgeit.fundoonote.configration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.bridgeit.fundoonote.model.User;



@Configuration
@ComponentScan(basePackages="com.bridgeit.fundoonote")
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:application.properties")
public class FundooNoteConfigration {

	@Autowired
	Environment environment;

	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter requestMappingHandlerAdapter = new RequestMappingHandlerAdapter();
		List<HttpMessageConverter<?>> messageConverters = new ArrayList();
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
	
	


}
