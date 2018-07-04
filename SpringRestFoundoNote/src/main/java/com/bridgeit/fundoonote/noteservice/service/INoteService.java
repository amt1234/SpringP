package com.bridgeit.fundoonote.noteservice.service;

import com.bridgeit.fundoonote.noteservice.model.Note;

public interface INoteService {

	public boolean createUserNote(Note note,long id);
	
}
