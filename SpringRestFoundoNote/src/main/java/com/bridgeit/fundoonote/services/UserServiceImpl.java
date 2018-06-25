package com.bridgeit.fundoonote.services;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
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
	public String save(User user) {
		if(userDao.checkEmail(user.getUserEmail())==null)
			return userDao.save(user);
		else
			return null;

	}

	@Override
	public boolean check(String userEmail,String password) {
		User user;
		if((user= userDao.checkEmail(userEmail)) == null)
			throw new RuntimeException("Invalid username");
		
		return BCrypt.checkpw(password, user.getPassword());
			
	}

}
