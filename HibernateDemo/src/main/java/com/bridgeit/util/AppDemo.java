package com.bridgeit.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.imageio.spi.ServiceRegistry;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bridgeit.model.Address;
import com.bridgeit.model.Laptop;
import com.bridgeit.model.Person;
import com.bridgeit.model.Student;
import com.bridgeit.model.User;
import com.bridgeit.model.Vehical;

public class AppDemo {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppDemo.class);
	

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		

		//To check cache levels -> create 2 differend sessions and close them and in hibernatecfg.xml mention the property for ehcache
		/*Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
		serviceRegistryBuilder.configure();
		StandardServiceRegistry registry = serviceRegistryBuilder.build();
		SessionFactory sessionFactory = configuration.buildSessionFactory(registry);
		
		Student student=new Student();

		Session session=sessionFactory.openSession();
		session.beginTransaction();
		student=(Student) session.get(Student.class, 101);
		System.out.println("Student cache"+student.hashCode());
		session.getTransaction().commit();
		session.close();
		
		Session session2=sessionFactory.openSession();
		session2.beginTransaction();
		student=(Student) session2.get(Student.class, 101);
		System.out.println("Student cache"+student.hashCode());
		session2.getTransaction().commit();
		session2.close();*/
		
		/*User user = new User();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the User name");
		String Username = scanner.next();
		user.setName(Username);*/
		
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
//		Student student=new Student();
//		student.setStudentId(101);
//		student.setStudentName("abc");
//		student.setStudentMarks(56);
//		session.save(student);
//		Student student2=new Student();
//		student2.setStudentId(102);
//		student2.setStudentName("xyz");
//		student2.setStudentMarks(56);
//		session.save(student2);
//		session.getTransaction().commit();
//		session.close();
		//LOGGER.error(student);
		
		/*Laptop laptop=new Laptop();
		laptop.setLaptioId(101);
		laptop.setLaptopName("Dell");
		
		Laptop laptop2=new Laptop();
		laptop.setLaptioId(102);
		laptop.setLaptopName("hp");

		student.getLaptops().add(laptop);
		student.getLaptops().add(laptop2);
		student2.getLaptops().add(laptop2);
		
		laptop.getStudents().add(student);
		laptop.getStudents().add(student2);
		laptop2.getStudents().add(student);
		*/
		
		
		/*Address address=new Address();
		
		address.setCity("mumbai");
		address.setPincode(400703);*/
		//user.setAddress(address);
		
		//for collection
		/*user.getListOfAddress().add(address);*/
		
		
		//one to one mapping
		/*Vehical vehical=new Vehical();
		vehical.setVehicalName("Brizza");
		user.setVehical(vehical);*/
		
		/*Person person=new Person();
		System.out.println("Enter the Person first name");
		String Personfname = scanner.next();
		person.setFirstname(Personfname);
		
		System.out.println("Enter the Person second name");
		String Personsname = scanner.next();
		person.setLastname(Personsname);*/
		
		// sessionFactory = new Configuration().configure().buildSessionFactory();
		// Session session = sessionFactory.openSession();

		// here used HibernateUtil which is singletone method for SessionFactory because sessionFactory is have heavy object
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		
		//HQL -> inserting multiple values
		/*
		Random random=new Random();
		for(int i=0;i<=50;i++)
		{
			Student student=new Student();
			student.setStudentId(i);
			student.setStudentName("abc"+i);
			student.setStudentMarks(random.nextInt(100));
			session.save(student);
		}
		
		session.getTransaction().commit();
		session.close();*/
		
		//To select all record from Student 
		/*Query q=session.createQuery(" from Student");
		List<Student> students=q.list();
		for(Student student:students)
		{
			System.out.println(student.getStudentId()+" "+student.getStudentName()+" "+student.getStudentMarks());
			//System.out.println(student[0]+" "+student[1]);
			//System.out.println("STUDENT:"+student);
		}
		session.getTransaction().commit();
		session.close();*/
		
		//To setMaxResult and setFirst REsult
		/*Query query=session.createQuery("from Student");
		query.setMaxResults(20);
		query.setFirstResult(10);
		List<Student> students=query.list();
		for(Student student:students)
		{
			System.out.println("student record :"+student.getStudentId()+" "+student.getStudentName()+" "+student.getStudentMarks());
		}
		session.getTransaction().commit();
		session.close();*/
		
		//To take input externally and uniqueResult
		/*Query query2=session.createQuery("from Student where studentMarks=:marks");
		query2.setInteger("marks", 62);
		Student  student=(Student) query2.uniqueResult();
		System.out.println(student.getStudentId()+" "+student.getStudentName()+" "+student.getStudentMarks());*/
		
		//To aliase aND IN parameter
		List titleList=new ArrayList();
		titleList.add("abc21");
		titleList.add("abc30");
		Query query=session.createQuery("from Student where studentName in (:titleList)");
		query.setParameterList("titleList", titleList);
		List<Student> students=query.list();
		for(Student student:students)
		{
			System.out.println("Student name list :"+student.getStudentId());
		}
		//retriving values from database
		/*Session session2=HibernateUtil.getSessionFactory().openSession();
		session2.beginTransaction();
		user=session2.get(User.class,1);
		System.out.println("user name retrive :"+user.getName());
		session2.close();*/
		
	}
}
