package com.bridgeit.inheritance2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
//@PrimaryKeyJoinColumn(name="Bicycle_ID")
public class MountainBicycle extends Bicycle {
/*
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Mid;
	public int getMid() {
		return Mid;
	}
	public void setMid(int mid) {
		this.Mid = mid;
	}*/
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	

}
