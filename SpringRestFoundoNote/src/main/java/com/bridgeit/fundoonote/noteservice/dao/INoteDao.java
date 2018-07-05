package com.bridgeit.fundoonote.noteservice.dao;

import java.util.List;

import com.bridgeit.fundoonote.noteservice.model.Note;

public interface INoteDao {

	public long create(Note note);
	public Note checkNoteId(long noteId);
	public boolean updateNote(Note note);
	public List<Note> notesList(long userid);
}
