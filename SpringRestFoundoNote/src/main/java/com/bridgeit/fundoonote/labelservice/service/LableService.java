package com.bridgeit.fundoonote.labelservice.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonote.labelservice.dao.ILableDao;
import com.bridgeit.fundoonote.labelservice.model.Label;
import com.bridgeit.fundoonote.labelservice.model.LabelDto;
import com.bridgeit.fundoonote.noteservice.dao.INoteDao;
import com.bridgeit.fundoonote.noteservice.model.Note;
import com.bridgeit.fundoonote.userservice.dao.UserDao;
import com.bridgeit.fundoonote.userservice.model.User;
import com.bridgeit.fundoonote.userservice.utility.IJwtProgram;

@Service
@Transactional
public class LableService implements ILableService {

	@Autowired
	IJwtProgram jwtToken;

	@Autowired
	UserDao userDao;

	@Autowired
	private ILableDao lableDao;
	@Autowired
	private INoteDao iNoteDao;

	@Override
	public boolean createLable(LabelDto labelDto, String token) {

		// getting userId from token
		long id = jwtToken.parseJWT(token);
		User user = userDao.checkId(id);

		String lableName = labelDto.getLabelName();
		LabelDto labelDto2 = new LabelDto();
		if (lableName != null) {
			labelDto2 = lableDao.checkByName(lableName);

			if (labelDto2 == null) {
				Label label = new Label();
				label.setUser(user);
				label.setLabelName(lableName);
				lableDao.createlable(label);
				return true;
			}
			return false;
		} else
			return false;
	}

	@Override
	public List<LabelDto> listOfAllLabels(String token) {

		List<LabelDto> labelDtos = new ArrayList<>();

		// getting userId from token
		long id = jwtToken.parseJWT(token);
		User user = userDao.checkId(id);

		List<Label> list = lableDao.getLabelList(user);

		for (Label label : list) {
			LabelDto labelDto2 = new LabelDto();
			labelDto2.setLabelId(label.getLabelId());
			labelDto2.setLabelName(label.getLabelName());

			labelDtos.add(labelDto2);
		}
		return labelDtos;
	}

	@Override
	public boolean updateLabels(String token, Label label) {
		// getting userId from token
		long id = jwtToken.parseJWT(token);
		User user = userDao.checkId(id);

		// getting userid from label table
		Label oldlabel = lableDao.checkById(label.getLabelId());

		if ((oldlabel.getUser().getUserId()) == (user.getUserId())) {
			oldlabel.setLabelId(label.getLabelId());
			oldlabel.setLabelName(label.getLabelName());
			lableDao.updateLabel(oldlabel);
			return true;
		} else
			return false;
	}

	@Override
	public boolean deleteLabels(String token, long labelId) {
		// getting userId from token
		long id = jwtToken.parseJWT(token);
		User user = userDao.checkId(id);

		Label label = lableDao.checkById(labelId);
		if ((label.getUser().getUserId()) == (user.getUserId())) {
			if (lableDao.deleteLabel(labelId))
				return true;
		}
		return false;
	}

	@Override
	public boolean addLabels(long noteId, LabelDto labelDto) {

		Note note = iNoteDao.checkNoteId(noteId);
		Label label = lableDao.checkById(labelDto.getLabelId());

		Set<Label> labelset = new HashSet<Label>();
		Set<Note> noteset = new HashSet<Note>();

		if ((note.getUser().getUserId()) == (label.getUser().getUserId())) {

			noteset = label.getNotes();
			noteset.add(note);
			label.setNotes(noteset);
			lableDao.updateLabel(label);

			labelset = note.getLabels();
			labelset.add(label);
			note.setLabels(labelset);
			iNoteDao.updateNote(note);
			return true;
		}
		return false;
	}

	@Override
	public boolean removeLables(long noteId, LabelDto labelDto) {
		Note note = iNoteDao.checkNoteId(noteId);
		Label label = lableDao.checkById(labelDto.getLabelId());

		Set<Label> labelset = new HashSet<Label>();
		Set<Note> noteset = new HashSet<Note>();

		if ((note.getUser().getUserId()) == (label.getUser().getUserId())) {
			noteset = label.getNotes();
			for (Note note2 : noteset) {
				if ((note2.getNoteId()) == noteId) {
					noteset.remove(note2);
					break;
				}
			}
			label.setNotes(noteset);
			labelset = note.getLabels();
			for (Label label2 : labelset) {
				if ((label2.getLabelId()) == (labelDto.getLabelId())) {
					labelset.remove(label2);
					break;
				}
			}
			note.setLabels(labelset);
			lableDao.updateLabel(label);
			iNoteDao.updateNote(note);
			return true;
		}
		return false;
	}
}
