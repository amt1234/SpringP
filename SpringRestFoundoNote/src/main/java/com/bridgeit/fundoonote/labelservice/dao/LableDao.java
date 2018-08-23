package com.bridgeit.fundoonote.labelservice.dao;


import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoonote.labelservice.model.Label;
import com.bridgeit.fundoonote.labelservice.model.LabelDto;
import com.bridgeit.fundoonote.userservice.model.User;

@Repository
public class LableDao implements ILableDao {

	@Autowired
	private SessionFactory sessionFactory;

	
	@Override 
	public long createlable(Label label) {
		return (long) sessionFactory.getCurrentSession().save(label);	
	}
	
	@Override
	public LabelDto checkByName(String labelName) {
		@SuppressWarnings("deprecation")
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Label.class)
				.add(Restrictions.eq("labelName", labelName));
		LabelDto labelDto = (LabelDto) criteria.uniqueResult();
		return labelDto;
	}
	
	@Override
	public Label checkById(long id)
	{
		@SuppressWarnings("deprecation")
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Label.class)
				.add(Restrictions.eq("labelId", id));
		Label label=(Label) criteria.uniqueResult();
		return label;
	}
	
	@Override
	public List<Label> getLabelList(User user)
	{
		@SuppressWarnings("deprecation")
		Criteria criteria=sessionFactory.getCurrentSession().createCriteria(Label.class)
				.add(Restrictions.eq("user", user));
		@SuppressWarnings("unchecked")
		List<Label> list=criteria.list();
		return list;
	}
	
	@Override
	public boolean updateLabel(Label label)
	{
		sessionFactory.getCurrentSession().save(label);
		return true;
	}
	
	@Override
	public boolean deleteLabel(long labelId) {
		Label label=checkById(labelId);
		sessionFactory.getCurrentSession().delete(label);
		return true;
	}
}
