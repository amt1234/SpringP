package com.bridgeit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.Dao.UserDao;
import com.bridgeit.model.User;

@Service
public class UserManagerImpl implements UserManager {

	UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<User> listAllUser() {

		return userDao.listAllUser();
	}

	@Override
	public int findUser() {

		return userDao.findUser();
	}

	@Override
	public int addUser(User user) {

		return userDao.addUser(user);
	}

	@Override
	public int updateUser(User user) {

		return userDao.updateUser(user);
	}

	@Override
	public int deleteUser(User user) {

		return userDao.deleteUser(user);
	}

}
