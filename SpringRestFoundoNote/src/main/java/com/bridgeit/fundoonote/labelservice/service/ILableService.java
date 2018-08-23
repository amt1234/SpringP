package com.bridgeit.fundoonote.labelservice.service;

import java.util.List;

import com.bridgeit.fundoonote.labelservice.model.Label;
import com.bridgeit.fundoonote.labelservice.model.LabelDto;

public interface ILableService {

	public boolean createLable(LabelDto labelDto,String token);
	public List<LabelDto> listOfAllLabels(String token);
	public boolean updateLabels(String token,Label label);
	public boolean deleteLabels(String token, long labelId);
	public boolean addLabels(long noteId,LabelDto labelDto);
	public boolean removeLables(long noteId, LabelDto labelDto);
}
