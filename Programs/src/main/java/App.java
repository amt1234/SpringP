import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bridgeit.services.ShapeService;

public class App {

	public static void main(String[] args) {
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("spring.xml");
		ShapeService shapeService=applicationContext.getBean("service",ShapeService.class);
//		String temp= shapeService.getTriangle().getName();
		System.out.println(shapeService.getTriangle().getName());
	}

}
