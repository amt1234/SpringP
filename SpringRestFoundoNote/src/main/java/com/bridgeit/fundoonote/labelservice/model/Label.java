package com.bridgeit.fundoonote.labelservice.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.bridgeit.fundoonote.noteservice.model.Note;
import com.bridgeit.fundoonote.userservice.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="LabelTable")
public class Label {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long labelId;
	private String labelName;
	
	@ManyToMany(cascade=CascadeType.ALL)
	@LazyCollection(value=LazyCollectionOption.FALSE)
	@JsonIgnore
	private Set<Note> notes=new HashSet<Note>();
	
	public Set<Note> getNotes() {
		return notes;
	}

	public void setNotes(Set<Note> notes) {
		this.notes = notes;
	}
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "userId")
	@JsonIgnore
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getLabelId() {
		return labelId;
	}

	public void setLabelId(long labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Label()
	{
		
	}
	public Label(long labelId,String labelName)
	{
		this.labelId=labelId;
		this.labelName=labelName;	
	}
	

}
