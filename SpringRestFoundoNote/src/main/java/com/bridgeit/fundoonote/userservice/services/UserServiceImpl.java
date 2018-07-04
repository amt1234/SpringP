package com.bridgeit.fundoonote.userservice.services;

import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonote.configration.FundooNoteConfigration;
import com.bridgeit.fundoonote.userservice.dao.UserDao;
import com.bridgeit.fundoonote.userservice.model.EmailInfo;
import com.bridgeit.fundoonote.userservice.model.RegistrationDTO;
import com.bridgeit.fundoonote.userservice.model.User;
import com.bridgeit.fundoonote.userservice.utility.IJwtProgram;
import com.bridgeit.fundoonote.userservice.utility.RedisConfigration;

import org.hibernate.SessionFactory;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	RabbitTemplate rabbitTemplate;

	SessionFactory sessionFactory;

	@Autowired
	IJwtProgram iJwtProgram;

	public UserServiceImpl(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public boolean registationSave(RegistrationDTO registrationDTO, String link) {

		LOGGER.info("START Registration service method");
		System.out.println("link..." + link);

		if (userDao.checkEmail(registrationDTO.getUserEmail()) == null) {
			User user = new User(registrationDTO);
			String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(pw_hash);
			long id = userDao.save(user);

			LOGGER.info("User Id :" + id);

			String token = iJwtProgram.createJWT(id, "poonam", "token_Generation", 86400000);
			System.out.println("Token : " + token);

			EmailInfo emailInfo = new EmailInfo();
			System.out.println("user email id :" + user.getUserEmail());
			emailInfo = new EmailInfo(user.getUserId(), token);
			emailInfo.setToken(token);
			emailInfo.setUserId(user.getUserId());
			emailInfo.setEmail(user.getUserEmail());
			emailInfo.setUrl(link);
			String tokenkey = emailInfo.getUserId() + "";

			RedisConfigration.redisUtil().set(tokenkey, token);

			LOGGER.info("server running");

			if (id > 0) {
				System.out.println("emailInfo ..." + emailInfo.getUserId() + " " + emailInfo.getToken());
				rabbitTemplate.convertAndSend(FundooNoteConfigration.topicExchangeName, "lazy.orange.rabbit",
						emailInfo);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	@Transactional
	public boolean getUserTokenVerify(String token) {

		System.out.println("user token service");

		long id = iJwtProgram.parseJWT(token);

		String synCommand = RedisConfigration.redisUtil().get(String.valueOf(id));

		if (token.equals(synCommand)) {
			System.out.println("update isActive status");

			User user = userDao.checkId(id);
			user.setActiveUser(true);

			if (userDao.updateUser(user))
				return true;
		} else
			return false;
		return false;
	}

	@Override
	public String check(String userEmail, String password) {
		LOGGER.info("START check service method");
		User user = userDao.checkEmail(userEmail);
		
		if (user != null) {
			long id = user.getUserId();
			user.setActiveUser(true);
			String token = iJwtProgram.createJWT(id, "poonam", "token_Generation", 86400000);
			System.out.println("Token : " + token);
			BCrypt.checkpw(password, user.getPassword());
			System.out.println("token from login service "+token);
			return token;
		} else 
			throw new RuntimeException("Invalid username");
	}

	@Override
	public boolean forgotUserPassword(String token, String password) {
		LOGGER.info("forgot password in service");

		System.out.println("user token service");

		long id = iJwtProgram.parseJWT(token);

		String synCommand = RedisConfigration.redisUtil().get(String.valueOf(id));

		System.out.println("token in verified user " + token);

		if (token.equals(synCommand)) {
			User user = userDao.checkId(id);
			String pw_hash = BCrypt.hashpw(password, BCrypt.gensalt());
			user.setPassword(pw_hash);
			return true;

		} else
			return false;
	}

	@Override
	public boolean resetUserPassword(EmailInfo emailInfo, String link) {
		User user = userDao.checkEmail(emailInfo.getEmail());

		String token = iJwtProgram.createJWT(user.getUserId(), "poonam", "token_Generation", 86400000);
		System.out.println("Token : " + token);

		emailInfo.setToken(token);
		emailInfo.setUserId(user.getUserId());
		emailInfo.setUrl(link);

		String tokenkey = String.valueOf(user.getUserId());
		RedisConfigration.redisUtil().set(tokenkey, token);
		System.out.println("user token service");

		rabbitTemplate.convertAndSend(FundooNoteConfigration.topicExchangeName, "lazy.orange.rabbit", emailInfo);
		return true;
	}
}
