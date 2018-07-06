package com.bridgeit.fundoonote.noteservice.service;

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
	private INoteDao noteDao;
	
	@Autowired
	UserDao userDao;
	
	@Autowired
	IJwtProgram jwtToken;

	@Override
	public Note createUserNote(Note note, String token) {

		// getting userId from token
		long id = jwtToken.parseJWT(token);
		User user = userDao.checkId(id);

		note.setUser(user);

		// getting noteId
		long noteid = noteDao.create(note);
		note = noteDao.checkNoteId(noteid);
		return note;
	}

	@Override
	@Transactional
	public boolean updateUserNote(Note note, String token) {

		// getting userId from token
		long id = jwtToken.parseJWT(token);
		User user = userDao.checkId(id);

		// getting userid from note table
		Note oldNote = noteDao.checkNoteId(note.getNoteId());

		if ((oldNote.getUser().getUserId()) == (user.getUserId())) {
			noteDao.updateNote(oldNote);
			return true;
		} else
			return false;
	}

	@Override
	public List<Note> listOfNote(String token) {
		
		// getting userId from token
		long id = jwtToken.parseJWT(token);
		User user = userDao.checkId(id);

		// passing user as parameter because in pojo there is user field
		List<Note> listOfNote = noteDao.notesList(user);
		return listOfNote;
	}

	@Override
	public boolean deleteUserNote(String token, long noteid) {
		long id = jwtToken.parseJWT(token);
		User user = userDao.checkId(id);

		Note note = noteDao.checkNoteId(noteid);
		if ((note.getUser().getUserId())==(user.getUserId())) {
			if (noteDao.deleteNoteOfUser(noteid))
				return true;
		}
		return false;
	}
}
