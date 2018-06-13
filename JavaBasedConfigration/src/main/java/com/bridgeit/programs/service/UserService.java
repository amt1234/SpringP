package com.bridgeit.programs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.model.User;
import com.bridgeit.programs.dao.IUserDao;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	public int addUser(User user) {
	
		return userDao.addUser(user);
	}
}
