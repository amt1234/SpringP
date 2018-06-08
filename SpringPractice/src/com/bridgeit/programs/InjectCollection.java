package com.bridgeit.programs;

import java.util.List;

public class InjectCollection {

	List<Point1> point;

	public List<Point1> getPoint() {
		return point;
	}

	public void setPoint(List<Point1> point) {
		this.point = point;
	}
	
	public void InjectM()
	{
		for(Point1 points: point)
		{
			System.out.println("point ="+points.getX()+" , "+points.getY());
		}
	}
	
	
}
