package com.bridgeit.programs;

public class Triangle {
	private String type;
	private int height;
	private Point1 point1;

	public Point1 getPoint() {
		return point1;
	}

	public void setPoint(Point1 point) {
		this.point1 = point;
		System.out.println("setPoint");
	}

	public int getHeight() {
		return height;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

	public void setHeight(int height) {
		this.height = height;

	}

	public Triangle(int height, String type) {
		System.out.println("Constructor with type and height argument ");
		this.type = type;
		this.height = height;
	}

	public Triangle() {
		System.out.println("default constructor of triangle class");
	}

	public void drawing() {
	
		System.out.println(getType() + " triangle type and height is " + getHeight());
	}

}
