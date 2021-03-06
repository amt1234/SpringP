package com.bridgeit.programs;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bridgeit.annotations.Circle;
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
		
//		Movie movie=(Movie) applicationContext.getBean("movie");
//		movie.movieMethod();
		
		//bean scope example
//		Triangle triangle=(Triangle) applicationContext.getBean("triangle");
//		triangle.setHeight(5);
//		triangle.setType("equilatral");
//		System.out.println("hash code :"+triangle.hashCode());
//		System.out.println("reference :"+triangle);
//		triangle.drawing();
//		Point point = triangle.getPoint();
//		
//		Triangle triangle2=(Triangle) applicationContext.getBean("triangle");
//		System.out.println("hash code :"+triangle2.hashCode());
//		System.out.println("reference :"+triangle2);
//		triangle2.drawing();
//		Point point2 = triangle2.getPoint();
//		
//		triangle.drawing();
//		System.out.println(point+"    "+point2);
		
//		PointDemo pointDemo=(PointDemo) applicationContext.getBean("points");
		
		
		Circle circle=applicationContext.getBean("circle",Circle.class);
		circle.draw();
		((ClassPathXmlApplicationContext) applicationContext).close();
	}
}
