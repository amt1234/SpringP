package com.bridgeit.fundoonote.noteservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
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
	
	@Override
	public Note checkNoteId(long noteId)
	{
		Criteria criteria=sessionFacetory.getCurrentSession().createCriteria(Note.class).add(Restrictions.eq("noteId", noteId));
		Note note=(Note) criteria.uniqueResult();
		return note;
		
	}
	
	@Override
	public boolean updateNote(Note note)
	{
		sessionFacetory.getCurrentSession().update(note);
		return true;
	}
	
	@Override
	public List<Note> notesList(long userid)
	{
		Criteria criteria=sessionFacetory.getCurrentSession().createCriteria(Note.class).add(Restrictions.eq("user",userid));
		 List<Note> list=criteria.list();
		return list;
		/*Query query=sessionFacetory.getCurrentSession().createQuery("from NoteTable where userId=userid");
		List<Note> list=query.list();
		return list;*/
		
	}
}
