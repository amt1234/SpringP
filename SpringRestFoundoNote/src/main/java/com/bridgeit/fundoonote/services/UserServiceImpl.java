package com.bridgeit.fundoonote.services;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonote.dao.UserDao;
import com.bridgeit.fundoonote.model.RegistrationDTO;
import com.bridgeit.fundoonote.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	

	@Override
	public boolean registationSave(RegistrationDTO registrationDTO) {
		
		User user=new User();
		user.setUserName(registrationDTO.getUserName());
		user.setUserEmail(registrationDTO.getUserEmail());
		user.setPassword(registrationDTO.getConfirmPassword());
		user.setPhoneNumber(registrationDTO.getPhoneNumber());
		user.setDateOfBirth(registrationDTO.getDateOfBirth());
		
		if (userDao.checkEmail(user.getUserEmail()) == null) {
			String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(pw_hash);
			int id = userDao.save(user);
			if (id > 0) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean check(String userEmail, String password) {
		User user = userDao.checkEmail(userEmail);
		if (user == null)
			throw new RuntimeException("Invalid username");
		return BCrypt.checkpw(password, user.getPassword());
	}

}
