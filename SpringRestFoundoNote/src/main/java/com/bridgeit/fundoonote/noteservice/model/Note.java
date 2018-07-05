package com.bridgeit.fundoonote.noteservice.model;

import java.util.Date;

import javax.jws.soap.SOAPBinding.Use;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import com.bridgeit.fundoonote.userservice.model.User;

@Entity
@Table(name="NoteTable")
public class Note {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long noteId;
	
	//@NotEmpty
	private String noteTitle;
	//@NotEmpty
	private String noteDescribtion;
	
	private boolean noteTrash;
	private boolean noteArchiev;
	private boolean notePinned;
	
	private Date createdDate;
	private String updatedDate;
	private String color;
	
	@ManyToOne
	@JoinColumn(referencedColumnName="userId")
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

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}	
}
