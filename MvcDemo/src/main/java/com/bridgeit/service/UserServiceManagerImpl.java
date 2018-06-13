package com.bridgeit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.Dao.UserDao;
import com.bridgeit.model.User;

@Service
public class UserServiceManagerImpl implements UserServiceManager {

	@Autowired
	UserDao userDao;

	@Override
	public List<User> listAllUser(User user) {

		return userDao.listAllUser(user);
	}

	@Override
	public Object findUser(User user) {

		System.out.println("dbbb"+user.getEmail());
		return userDao.findUser(user);
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
