package com.bridgeit.fundoonote.noteservice.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonote.noteservice.dao.INoteDao;
import com.bridgeit.fundoonote.noteservice.model.Note;
import com.bridgeit.fundoonote.userservice.dao.UserDao;
import com.bridgeit.fundoonote.userservice.model.User;

@Service
@Transactional
public class NoteService implements INoteService{

	@Autowired
	private INoteDao iNoteDao;
	UserDao userDao;
	@Override
	public boolean createUserNote(Note note,long id) {
	
		SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss aa");
		dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
		String date=dateTimeInGMT.format(new Date());
		note.setCreatedDate(date);
		
		/*User user=userDao.checkId(id);
		Note note2=iNoteDao.create(user);*/
		//long noteid=iNoteDao.create(user);
		return true;
		
	}

}
