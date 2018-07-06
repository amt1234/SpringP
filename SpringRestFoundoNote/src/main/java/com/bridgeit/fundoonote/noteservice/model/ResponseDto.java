package com.bridgeit.fundoonote.noteservice.model;

import java.util.Date;

public class ResponseDto {

	private long noteId;

	private String noteTitle;

	private String noteDescribtion;

	private boolean noteTrash;
	private boolean noteArchiev;
	private boolean notePinned;

	private Date createdDate;
	private Date updatedDate;
	private String color;

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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public ResponseDto(Note note) {
		this.noteId = note.getNoteId();
		this.noteTitle = note.getNoteTitle();
		this.noteDescribtion = note.getNoteDescribtion();
		this.noteTrash = note.isNoteTrash();
		this.noteArchiev = note.isNoteArchiev();
		this.notePinned = note.isNotePinned();
		this.createdDate = note.getCreatedDate();
		this.updatedDate = note.getUpdatedDate();
		this.color = note.getColor();
	}

	public ResponseDto() {
	}

}
