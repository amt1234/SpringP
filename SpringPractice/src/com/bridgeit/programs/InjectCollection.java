package com.bridgeit.programs;

import java.util.List;

public class InjectCollection {

	List<Point> point;

	public List<Point> getPoint() {
		return point;
	}

	public void setPoint(List<Point> point) {
		this.point = point;
	}
	
	public void InjectM()
	{
		for(Point points: point)
		{
			System.out.println("point ="+points.getX()+" , "+points.getY());
		}
	}
	
	
}
