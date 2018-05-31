package com.bridgeit.autowired;

import com.bridgeit.programs.Point;

public class Movie {

	private int ticket;
	private String name;
	private Point point1;
	
	public Point getPoint() {
		return point1;
	}
	public void setPoint(Point point) {
		System.out.println("set point in movie ");
		this.point1 = point;
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
		//System.out.println("point: {"+getPoint().getX()+" ,"+getPoint().getY()+" }");
	}
	
}
