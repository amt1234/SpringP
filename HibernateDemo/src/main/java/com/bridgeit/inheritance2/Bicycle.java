package com.bridgeit.inheritance2;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
//@Inheritance(strategy=InheritanceType.JOINED)
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class Bicycle {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	//@Column(name="Bicycle_ID")
	private int id;
	
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
