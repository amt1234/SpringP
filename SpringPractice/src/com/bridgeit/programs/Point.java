package com.bridgeit.programs;

public class Point {

	private int x;
	private int y;

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	 public Point() {
		
		System.out.println("Point default const"+getX()+" "+getY());
	}
	
	 public Point(int x,int y)
	 {
		 this.x=x;
		 this.y=y;
		 System.out.println("point with 2 argument "+getX()+" and "+getY());
	 }
	
}
