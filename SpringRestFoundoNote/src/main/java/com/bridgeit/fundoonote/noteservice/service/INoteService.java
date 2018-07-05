package com.bridgeit.fundoonote.noteservice.service;

import java.util.List;

import com.bridgeit.fundoonote.noteservice.model.Note;

public interface INoteService {

	public Note createUserNote(Note note,String token);
	public boolean updateUserNote(Note note,String token);
	public List<Note> listofNote(String token);
	
}
