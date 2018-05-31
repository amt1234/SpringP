package com.bridgeit.programs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bridgeit.autowired.Movie;

public class App {

	public static void main(String[] args) {
//		Resource resource=new FileSystemResource("spring.xml");
//		BeanFactory beanfactory=new XmlBeanFactory(resource);
		ApplicationContext applicationContext= new ClassPathXmlApplicationContext("spring.xml");
//		PointDemo pointdemo=(PointDemo) applicationContext.getBean("point");
//		pointdemo.pointGet();
		
//		InjectCollection injectCollection=(InjectCollection) applicationContext.getBean("point");
//		injectCollection.InjectM();
		
		Movie movie=(Movie) applicationContext.getBean("movie");
		movie.movieMethod();
		
//		Triangle triangle=(Triangle) applicationContext.getBean("triangle");
//		triangle.drawing();
		
	}
}
