package com.bridgeit.fundoonote.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoonote.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	SessionFactory sessionFacetory;

	@Override
	public String save(User user) {
		String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(pw_hash);
		sessionFacetory.getCurrentSession().save(user);
		return user.getUserEmail();
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

	@Override
	public boolean checkPassword(String password) {
		if (BCrypt.checkpw(password, password))
			return true;
		else
			return false;
	}

}
