package com.bridgeit.fundoonote.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonote.dao.UserDao;
import com.bridgeit.fundoonote.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public long save(User user) {
		
		 userDao.save(user);
		 return 0;
	}

	
}
