package com.bridgeit.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

//@Entity
@Table(name="OneToOne_with_UserAndVehical")
public class User {

	//@Id
	//@Column(name="User_Id")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private int id;
	
	//@Transient ->used to don't show this column in database
	private String name;
	
	//@Embedded ->used to show Address class is embeddable
	/*private Address address;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}*/
	
	//to show the collection used here
	/*@ElementCollection
	private Set<Address> listOfAddress=new HashSet<Address>();
	
	public Set<Address> getListOfAddress() {
		return listOfAddress;
	}
	public void setListOfAddress(Set<Address> listOfAddress) {
		this.listOfAddress = listOfAddress;
	}*/
	
	
	//to show one to one mapping
//	@OneToOne(cascade=CascadeType.ALL)
	//@JoinColumn(name="vehicalId")
	private Vehical vehical;
	
	public Vehical getVehical() {
		return vehical;
	}
	public void setVehical(Vehical vehical) {
		this.vehical = vehical;
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
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + "]";
	}
	
}
