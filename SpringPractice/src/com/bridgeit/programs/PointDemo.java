package com.bridgeit.programs;

public class PointDemo {
	public Point pointA;
	public Point pointB;
	public Point pointC;
	
	public Point getPointA() {
		return pointA;
	}
	public void setPointA(Point pointA) {
		this.pointA = pointA;
	}
	public Point getPointB() {
		return pointB;
	}
	public void setPointB(Point pointB) {
		this.pointB = pointB;
	}
	public Point getPointC() {
		return pointC;
	}
	public void setPointC(Point pointC) {
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
