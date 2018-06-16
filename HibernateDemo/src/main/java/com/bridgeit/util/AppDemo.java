package com.bridgeit.util;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bridgeit.model.Address;
import com.bridgeit.model.Person;
import com.bridgeit.model.User;
import com.bridgeit.model.Vehical;

public class AppDemo {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AppDemo.class);

	public static void main(String[] args) {

		User user = new User();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the User name");
		String Username = scanner.next();
		user.setName(Username);
		
		LOGGER.error(Username);

		Address address=new Address();
		
		address.setCity("mumbai");
		address.setPincode(400703);
		//user.setAddress(address);
		
		//for collection
		/*user.getListOfAddress().add(address);*/
		
		
		//one to one mapping
		Vehical vehical=new Vehical();
		vehical.setVehicalName("Brizza");
		user.setVehical(vehical);
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
		session.persist(user);
		//session.save(person);
		//session.save(vehical);
		session.getTransaction().commit();
		session.close();
		scanner.close();

		//retriving values from database
		/*Session session2=HibernateUtil.getSessionFactory().openSession();
		session2.beginTransaction();
		user=session2.get(User.class,1);
		System.out.println("user name retrive :"+user.getName());
		session2.close();*/
		
		
		
	}
}
