package com.bridgeit.springrest.config;

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
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import com.bridgeit.dao.IUserDao;
import com.bridgeit.dao.UserDao;
import com.bridgeit.springrest.dao.EmployeeDao;
import com.bridgeit.springrest.dao.EmployeeDaoImpl;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.bridgeit.springrest")
@PropertySource("classpath:application.properties")
public class SpringRestConfigration {

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
	public EmployeeDao employeeDaoImpl() {
		return new EmployeeDaoImpl();
	}
	
	/*
	 * @Bean public ViewResolver viewResolver() { InternalResourceViewResolver
	 * internalResourceViewResolver=new InternalResourceViewResolver();
	 * internalResourceViewResolver.setPrefix("/WEB-INF/views/");
	 * internalResourceViewResolver.setSuffix(".jsp"); return
	 * internalResourceViewResolver; }
	 */

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/RegisterDB");
		driverManagerDataSource.setUsername("root");
		driverManagerDataSource.setPassword("root");

		return driverManagerDataSource;
	}

	/*
	 * @Bean public JdbcTemplate jdbcTemplate(DataSource dataSource) { JdbcTemplate
	 * jdbcTemplate=new JdbcTemplate(dataSource); return jdbcTemplate;
	 * 
	 * }
	 */

	@Bean
	public SessionFactory getSessionFactory() throws IOException {

		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setPackagesToScan("com.bridgeit.springrest");
		
		Properties properties = new Properties();
		properties.put("hibernate_dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate_show_sql",environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate_hbm2ddl.auto",environment.getRequiredProperty("hibernate.hbm2ddl.auto"));

		sessionFactoryBean.setHibernateProperties(properties);
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.afterPropertiesSet();

		return sessionFactoryBean.getObject();
	}

	@Bean
	public HibernateTransactionManager getTransactionManager() throws IOException {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(getSessionFactory());

		return transactionManager;
	}

}
