package com.bridgeit.fundoonote.noteservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoonote.noteservice.model.Note;
import com.bridgeit.fundoonote.noteservice.model.WebScrap;
import com.bridgeit.fundoonote.userservice.model.User;

@Repository
public class NoteDao implements INoteDao {

	@Autowired
	private SessionFactory sessionFacetory;

	@Override
	public long create(Note note) {
		System.out.println("dao of note");
		return (long) sessionFacetory.getCurrentSession().save(note);
	}

	@Override
	public Note checkNoteId(long noteId) {
		@SuppressWarnings("deprecation")
		Criteria criteria = sessionFacetory.getCurrentSession().createCriteria(Note.class)
				.add(Restrictions.eq("noteId", noteId));
		Note note = (Note) criteria.uniqueResult();
		return note;
	}
	
	@Override
	public boolean updateNote(Note note) {
		sessionFacetory.getCurrentSession().update(note);
		return true;
	}

	@Override
	public List<Note> notesList(User user) {
		@SuppressWarnings("deprecation")
		Criteria criteria = sessionFacetory.getCurrentSession().createCriteria(Note.class)
				.add(Restrictions.eq("user", user));
		@SuppressWarnings("unchecked")
		List<Note> list = criteria.list();
		return list;
	}

	@Override
	public List<Note> collaboratedNoteList(){
		Criteria criteria=sessionFacetory.getCurrentSession().createCriteria(Note.class);
		List<Note> list=criteria.list();
		return list;	
	}
	
	@Override
	public boolean deleteNoteOfUser(long noteId) {
		System.out.println("note id in dao" + noteId);
		Note note = checkNoteId(noteId);
		sessionFacetory.getCurrentSession().delete(note);
		return true;
	}
	
	@Override
	public boolean createWebscrap(WebScrap webScrap) {
		sessionFacetory.getCurrentSession().save(webScrap);
		return true;
	}
	
	@Override
	public WebScrap checkWebScrapLInkId(long linkId) {
		Criteria criteria = sessionFacetory.getCurrentSession().createCriteria(WebScrap.class)
				.add(Restrictions.eq("linkId", linkId));
		WebScrap webScrap = (WebScrap) criteria.uniqueResult();
		return webScrap;	
	}
	
	@Override
	public boolean removeWebScrap(long linkId) {
		WebScrap webScrap=checkWebScrapLInkId(linkId);
		sessionFacetory.getCurrentSession().delete(webScrap);
		return true;
	}
	
}
