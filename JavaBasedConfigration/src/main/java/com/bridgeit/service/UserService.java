package com.bridgeit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.dao.IUserDao;
import com.bridgeit.model.User;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	public int addUser(User user) {
	
		return userDao.addUser(user);
	}
}
