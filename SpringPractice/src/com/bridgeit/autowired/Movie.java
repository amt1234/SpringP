package com.bridgeit.autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.bridgeit.programs.Point1;

public class Movie {

	private int ticket;
	private String name;
	private Point1 point1;
	
	public Point1 getPoint() {
		return point1;
	}
	//setter use only for byName and byType
	public void setPoint1(Point1 point1) {
		System.out.println("set point in movie ");
		this.point1 = point1;
	}
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public void movieMethod()
	{
		System.out.println("Movie info :"+getName()+" and ticket no :"+getTicket());
		System.out.println(point1.getX()+"   "+point1.getY());
		//System.out.println("point: {"+getPoint().getX()+" ,"+getPoint().getY()+" }");
	}
	
}
