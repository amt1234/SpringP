package com.bridgeit.springrest.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	private int id;
	private String name;
	private String createDate;

	
	public Employee(int id, String name, String createDate) {
		super();
		this.id = id;
		this.name = name;
		this.createDate = createDate;
	}
	public Employee()
	{
		
	}

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

	//@JsonSerialize(using = DateSerializer.class)
	public String getCreateDate() {
		return createDate;
	}

	public String setCreateDate(String createDate) {
		return this.createDate = createDate;
	}

}
