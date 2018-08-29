package com.bridgeit.fundoonote.noteservice.model;

import java.util.Date;
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

import com.bridgeit.fundoonote.labelservice.model.Label;
import com.bridgeit.fundoonote.userservice.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "NoteTable")
public class Note {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long noteId;

	private String noteTitle;
	private String noteDescribtion;

	private boolean noteTrash;
	private boolean noteArchiev;
	private boolean notePinned;

	private Date createdDate = new Date();
	private Date updatedDate = new Date();
	private Date reminderDate = new Date();
	private String reminderTime;
	private String image;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@ManyToMany(mappedBy="notes")
	@LazyCollection(value=LazyCollectionOption.FALSE)
	@JsonIgnore
	private Set<Label> labels=new HashSet<Label>();
	
	public Set<Label> getLabels() {
		return labels;
	}

	public void setLabels(Set<Label> labels) {
		this.labels = labels;
	}
	
	//for collaborator
	@ManyToMany(cascade=CascadeType.ALL)
	@LazyCollection(value=LazyCollectionOption.FALSE)
	private Set<User> userset=new HashSet<User>();

	public Set<User> getUserset() {
		return userset;
	}

	public void setUserset(Set<User> userset) {
		this.userset = userset;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date date) {
		this.createdDate = new Date();
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date date) {
		this.updatedDate = new Date();
	}

	public long getNoteId() {
		return noteId;
	}

	public void setNoteId(long noteId) {
		this.noteId = noteId;
	}

	public String getNoteTitle() {
		return noteTitle;
	}

	public void setNoteTitle(String noteTitle) {
		this.noteTitle = noteTitle;
	}

	public String getNoteDescribtion() {
		return noteDescribtion;
	}

	public void setNoteDescribtion(String noteDescribtion) {
		this.noteDescribtion = noteDescribtion;
	}

	public boolean isNoteTrash() {
		return noteTrash;
	}

	public void setNoteTrash(boolean noteTrash) {
		this.noteTrash = noteTrash;
	}

	public boolean isNoteArchiev() {
		return noteArchiev;
	}

	public void setNoteArchiev(boolean noteArchiev) {
		this.noteArchiev = noteArchiev;
	}

	public boolean isNotePinned() {
		return notePinned;
	}

	public void setNotePinned(boolean notePinned) {
		this.notePinned = notePinned;
	}

	public String getReminderTime() {
		return reminderTime;
	}

	public void setReminderTime(String reminderTime) {
		this.reminderTime = reminderTime;
	}

	private String color;

	public Date getReminderDate() {
		return reminderDate;
	}

	public void setReminderDate(Date reminderDate) {
		this.reminderDate = reminderDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Note(long noteId, String noteTitle, String noteDescribtion, boolean noteTrash, boolean noteArchiev,
			boolean notePinned, Date createdDate, Date updatedDate, Date reminderDate, String reminderTime,
			String color,String image) {
		this.noteId = noteId;
		this.noteTitle = noteTitle;
		this.noteDescribtion = noteDescribtion;
		this.noteTrash = noteTrash;
		this.noteArchiev = noteArchiev;
		this.notePinned = notePinned;
		this.createdDate = createdDate;
		this.updatedDate = updatedDate;
		this.reminderDate = reminderDate;
		this.reminderTime = reminderTime;
		this.color = color;
		this.image=image;
	}

	public Note() {
	}

}
