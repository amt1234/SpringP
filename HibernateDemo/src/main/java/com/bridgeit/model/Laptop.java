package com.bridgeit.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Laptop {

	@Id
	private int laptioId;
	private String laptopName;

	/*@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(referencedColumnName="studentId")
	private Student student;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}*/
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Student> students=new ArrayList<Student>();
	
	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public int getLaptioId() {
		return laptioId;
	}

	public void setLaptioId(int laptioId) {
		this.laptioId = laptioId;
	}

	public String getLaptopName() {
		return laptopName;
	}

	public void setLaptopName(String laptopName) {
		this.laptopName = laptopName;
	}
}
