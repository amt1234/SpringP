package com.bridgeit.inheritance2;

import javax.persistence.Entity;

@Entity
public class SwingBicycle extends Bicycle{

	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
