package com.bridgeit.inheritance;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorValue(value="executive")
public class Excecutive extends Employee {

	/*@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int excId;*/
	private String executiveName;
	/*public int getExcId() {
		return excId;
	}
	public void setExcId(int excId) {
		this.excId = excId;
	}*/
	public String getExecutiveName() {
		return executiveName;
	}
	public void setExecutiveName(String executiveName) {
		this.executiveName = executiveName;
	}
}
