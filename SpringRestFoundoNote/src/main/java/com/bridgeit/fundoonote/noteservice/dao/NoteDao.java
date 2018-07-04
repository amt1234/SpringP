package com.bridgeit.fundoonote.noteservice.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoonote.noteservice.model.Note;

@Repository
public class NoteDao implements INoteDao{

	@Autowired
	SessionFactory sessionFacetory;

	@Override
	public long create(Note note) {
		System.out.println("dao of note");
		return (long) sessionFacetory.getCurrentSession().save(note);
		
	}

}
