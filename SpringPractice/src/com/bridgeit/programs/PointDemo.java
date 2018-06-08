package com.bridgeit.programs;

public class PointDemo {
	public Point1 pointA;
	public Point1 pointB;
	public Point1 pointC;
	
	public Point1 getPointA() {
		return pointA;
	}
	public void setPointA(Point1 pointA) {
		this.pointA = pointA;
	}
	public Point1 getPointB() {
		return pointB;
	}
	public void setPointB(Point1 pointB) {
		this.pointB = pointB;
	}
	public Point1 getPointC() {
		return pointC;
	}
	public void setPointC(Point1 pointC) {
		this.pointC = pointC;
	}
	
	public PointDemo()
	{
		System.out.println("default constructor pointdemo class");
	}
	public void pointGet()
	{
		System.out.println("pointA x ="+getPointA().getX()+"and y="+getPointA().getY());
		System.out.println("pointB x ="+getPointB().getX()+"and y="+getPointB().getY());
		System.out.println("pointC x ="+getPointC().getX()+"and y="+getPointC().getY());
	}
}
