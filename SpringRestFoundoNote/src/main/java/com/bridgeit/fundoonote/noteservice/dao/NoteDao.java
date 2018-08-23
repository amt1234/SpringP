package com.bridgeit.fundoonote.noteservice.dao;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.bridgeit.fundoonote.noteservice.model.Note;
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
	public boolean deleteNoteOfUser(long noteId) {
		System.out.println("note id in dao" + noteId);
		Note note = checkNoteId(noteId);
		sessionFacetory.getCurrentSession().delete(note);
		return true;
	}

	public boolean uploadFile(byte[] bytes, File serverFile) {
		try {
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			
			stream.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		return true;

	}
}
