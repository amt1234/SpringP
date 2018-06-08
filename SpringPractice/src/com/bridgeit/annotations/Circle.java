package com.bridgeit.annotations;

import com.bridgeit.programs.Point1;

public class Circle {

	private Point1 center;

	public Point1 getCenter() {
		return center;
	}

	public void setCenter(Point1 center) {
		this.center = center;
	}
	
	public void draw()
	{
		System.out.println("Drawing method");
	}
}
