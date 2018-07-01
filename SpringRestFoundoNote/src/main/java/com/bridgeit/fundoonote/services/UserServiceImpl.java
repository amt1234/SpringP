package com.bridgeit.fundoonote.services;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonote.configration.FundooNoteConfigration;
import com.bridgeit.fundoonote.dao.UserDao;
import com.bridgeit.fundoonote.jwt.JwtProgram;
import com.bridgeit.fundoonote.model.EmailInfo;
import com.bridgeit.fundoonote.model.RegistrationDTO;
import com.bridgeit.fundoonote.model.User;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;

	RabbitTemplate rabbitTemplate;

	EmailInfo emailInfo;

	public UserServiceImpl(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@Override
	public boolean registationSave(RegistrationDTO registrationDTO) {

		LOGGER.info("START Registration service method");
		User user = new User();

		user.setUserName(registrationDTO.getUserName());
		user.setUserEmail(registrationDTO.getUserEmail());
		user.setPassword(registrationDTO.getConfirmPassword());
		user.setPhoneNumber(registrationDTO.getPhoneNumber());
		user.setDateOfBirth(registrationDTO.getDateOfBirth());

		if (userDao.checkEmail(user.getUserEmail()) == null) {
			String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(pw_hash);
			long id = userDao.save(user);

			LOGGER.info("User Id :" + id);
			JwtProgram jwtProgram = new JwtProgram();
			String token = jwtProgram.createJWT(id, "poonam", "token_Generation", 1000);
			System.out.println("Token : " + token);

			System.out.println("user email id :" + user.getUserEmail());
			emailInfo = new EmailInfo(user.getUserEmail(), token);
			emailInfo.setToken(token);
			emailInfo.setEmail(user.getUserEmail());
			RedisClient redisClient = RedisClient.create("redis://@localhost:6379/");
			// RedisClient redisClient = RedisClient.create(new RedisURI("localhost",
			// 6379,120,TimeUnit.SECONDS));
			StatefulRedisConnection<String, String> connection = redisClient.connect();
			RedisCommands<String, String> syncCommands = connection.sync();

			String tokenkey = emailInfo.getEmail();
			LOGGER.info("TokenKey" + tokenkey);

			syncCommands.set(tokenkey, token);
			LOGGER.info("server running");

			if (id > 0) {
				System.out.println("HELLO ID");
				System.out.println("emailInfo ..." + emailInfo.getEmail() + " " + emailInfo.getToken());
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
		RedisClient redisClient = RedisClient.create("redis://@localhost:6379/");
		StatefulRedisConnection<String, String> connection = redisClient.connect();
		RedisCommands<String, String> syncCommands = connection.sync();

		LOGGER.info("emailInfo... :" + emailInfo.getEmail());
		System.out.println("looger... " + emailInfo.getEmail());
		System.out.println("email token is.." + emailInfo.getToken());

		System.out.println("synCommand :" + syncCommands.get(emailInfo.getEmail()));
		String synCommand = syncCommands.get(emailInfo.getEmail());
		System.out.println("token in verified user " + token);
		if (token.equals(synCommand)) {
			System.out.println("update isActive status");
			User user = new User();
			// user.setActiveUser(userDao.updateUser(isActiveUser));
			return true;
		} else
			return false;
	}

	@Override
	public boolean check(String userEmail, String password) {
		LOGGER.info("START check service method");
		User user = userDao.checkEmail(userEmail);
		if (user == null)
			throw new RuntimeException("Invalid username");
		return BCrypt.checkpw(password, user.getPassword());
	}

	@Override
	public boolean forgotUserPassword(RegistrationDTO registrationDTO, String emailId,String token) {
		LOGGER.info("forgot password in service");
		if(getUserTokenVerify(token)) 
		{
			if (userDao.checkEmail(emailId).equals(registrationDTO.getUserEmail())) {
			User user = new User();
			user.setPassword(registrationDTO.getConfirmPassword());
			String pw_hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			user.setPassword(pw_hash);
			long id = userDao.save(user);

			if (id > 0)
				return true;
			else
				return false;
		}
		return false;
	}
		return false;
	}
}
