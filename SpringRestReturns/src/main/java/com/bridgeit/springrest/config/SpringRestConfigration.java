package com.bridgeit.springrest.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.bridgeit.springrest")
public class SpringRestConfigration {

	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter()
	{
		RequestMappingHandlerAdapter requestMappingHandlerAdapter=new RequestMappingHandlerAdapter();
		List<HttpMessageConverter<?>> messageConverters=new ArrayList<>();
		messageConverters.add(mappingJackson2HttpMessageConverter());
		requestMappingHandlerAdapter.setMessageConverters(messageConverters);
		return requestMappingHandlerAdapter;
	}
	
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter()
	{
		return new MappingJackson2HttpMessageConverter();
	}
	
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver internalResourceViewResolver=new InternalResourceViewResolver();
		internalResourceViewResolver.setPrefix("/WEB-INF/views/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;
	}
}
