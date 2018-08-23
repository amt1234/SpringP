package com.bridgeit.fundoonote.labelservice.dao;

import java.util.List;

import com.bridgeit.fundoonote.labelservice.model.Label;
import com.bridgeit.fundoonote.labelservice.model.LabelDto;
import com.bridgeit.fundoonote.userservice.model.User;

public interface ILableDao {

	public long createlable(Label label);
	public LabelDto checkByName(String lableName);
	public Label checkById(long id);
	public List<Label> getLabelList(User user);
	public boolean updateLabel(Label label);
	public boolean deleteLabel(long labelId);
	
}
