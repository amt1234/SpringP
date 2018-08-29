package com.bridgeit.fundoonote.userservice.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoonote.userservice.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

	@Autowired
	SessionFactory sessionFacetory;

	@Override
	public long save(User user) {
		LOGGER.info("start save method of dao");
		return (long) sessionFacetory.getCurrentSession().save(user);
	}

	@Override
	public boolean login(long userId) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public User checkEmail(String userEmail) {
		LOGGER.info("start checkEmail method of dao");
		Criteria criteria = sessionFacetory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("userEmail", userEmail));
		User user = (User) criteria.uniqueResult();
		System.out.println("user in dao..." + userEmail);
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		sessionFacetory.getCurrentSession().update(user);
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public User checkId(long userId) {
		Criteria criteria = sessionFacetory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("userId", userId));
		User user = (User) criteria.uniqueResult();
		System.out.println("user in dao..." + userId);
		return user;
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	@Override
	public List<User> userList() {
		Criteria criteria = sessionFacetory.getCurrentSession().createCriteria(User.class);
		List<User> list = criteria.list();
		return list;
	}
}
