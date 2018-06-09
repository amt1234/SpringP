package com.bridgeit.module;

public class Triangle {
	private String name;

	public String getName() {
		System.out.println("inside -> getName");
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void triangleShape()
	{
		System.out.println("triangle method");
	}
}
