package com.bridgeit.fundoonote.noteservice.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonote.noteservice.dao.INoteDao;
import com.bridgeit.fundoonote.noteservice.model.Note;
import com.bridgeit.fundoonote.userservice.dao.UserDao;
import com.bridgeit.fundoonote.userservice.model.User;
import com.bridgeit.fundoonote.userservice.utility.IJwtProgram;

@Service
@Transactional
public class NoteService implements INoteService {

	@Autowired
	private INoteDao iNoteDao;
	@Autowired
	UserDao userDao;
	@Autowired
	IJwtProgram ijwtprogram;

	@Override
	public Note createUserNote(Note note, String token) {
		Date date = new Date();
		note.setCreatedDate(date);

		// getting userId from token
		long id = ijwtprogram.parseJWT(token);
		User user = userDao.checkId(id);

		note.setUser(user);

		// getting noteId
		long noteid = iNoteDao.create(note);
		note = iNoteDao.checkNoteId(noteid);
		return note;
	}

	@Override
	@Transactional
	public boolean updateUserNote(Note note, String token) {
		Date date = new Date();
		note.setCreatedDate(date);

		// getting userId from token
		long id = ijwtprogram.parseJWT(token);
		User user = userDao.checkId(id);

		// getting userid from note table
		Note note2 = iNoteDao.checkNoteId(note.getNoteId());

		if ((note2.getUser().getUserId()) == (user.getUserId())) {
			note2.setColor(note.getColor());
			//note2.setNoteDescribtion(note.getNoteDescribtion());
			iNoteDao.updateNote(note2);
			return true;
		} else
			return false;
	}

	@Override
	public List<Note> listofNote(String token) {
		// getting userId from token
		long id = ijwtprogram.parseJWT(token);
		User user = userDao.checkId(id);
		
		List<Note> notelist=iNoteDao.notesList(user.getUserId());
		for(Note n:notelist)
		{
			System.out.println("list of user :"+n.getNoteId()+" "+n.getNoteTitle()+" "+n.getColor());
		}
		return notelist;

	}

}
