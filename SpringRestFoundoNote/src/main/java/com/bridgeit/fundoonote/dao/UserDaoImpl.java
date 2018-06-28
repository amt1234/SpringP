package com.bridgeit.fundoonote.dao;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoonote.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFacetory;

	@Override
	public int save(User user) {
		return (int) sessionFacetory.getCurrentSession().save(user);
	}

	@Override
	public boolean login(long userId) {
		return false;
	}

	@Override
	public User checkEmail(String userEmail) {
		Criteria criteria=sessionFacetory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("userEmail", userEmail));
		User user=(User)criteria.uniqueResult();
		return user;
	}

	/*@Override
	public boolean checkPassword(String password) {
		if (BCrypt.checkpw(password, password))
			return true;
		else
			return false;
	}*/

}
