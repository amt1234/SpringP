package com.bridgeit.programs;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.bridgeit.dao.IUserDao;
import com.bridgeit.dao.UserDao;
import com.bridgeit.service.IUserService;
import com.bridgeit.service.UserService;

@EnableWebMvc
@Configuration
@ComponentScan(basePackages="com.bridgeit")
@PropertySource(value= {"classpath:application.properties"})

public class SpringConfig implements WebMvcConfigurer{
	
	@Autowired
	private Environment environment;
	
	@Bean
	public ViewResolver viewResolver()
	{
		InternalResourceViewResolver internalResourceViewResolver=new InternalResourceViewResolver();
		internalResourceViewResolver.setViewClass(JstlView.class);
		internalResourceViewResolver.setPrefix("/WEB-INF/view/");
		internalResourceViewResolver.setSuffix(".jsp");
		return internalResourceViewResolver;	
	}
	
	
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public IUserDao userDao() {
		return new UserDao();
	}
	
	@Bean
	public IUserService userSevice() {
		return new UserService();
		
	}
	
	/*@Bean
	public JdbcTemplate jdbctemplate(DataSource dataSource) {
		return new JdbcTemplate();	
	}*/
	 
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
  
        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
        return dataSource;
    }
	@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        //jdbcTemplate.setResultsMapCaseInsensitive(true);
        return jdbcTemplate;
    }

}