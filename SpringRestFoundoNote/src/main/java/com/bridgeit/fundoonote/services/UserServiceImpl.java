package com.bridgeit.fundoonote.services;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

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

	public UserServiceImpl(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	EmailInfo emailinfo;
	

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

			RedisClient redisClient = RedisClient.create("redis://@localhost:6379/");
			// RedisClient redisClient = RedisClient.create(new RedisURI("localhost", 6379,
			// 120,TimeUnit.SECONDS));
			StatefulRedisConnection<String, String> connection = redisClient.connect();
			RedisCommands<String, String> syncCommands = connection.sync();
			String key = userDao.userEmailRadies(user);
			LOGGER.info("key..." + key);
			syncCommands.set(key, token);

			LOGGER.info("server running");
			if (id > 0) {
				System.out.println("HELLO ID");
				rabbitTemplate.convertAndSend(FundooNoteConfigration.topicExchangeName, "lazy.orange.rabbit", token);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean getUserTokenVerify(String token) {
		RedisClient redisClient = RedisClient.create("redis://@localhost:6379/");
		StatefulRedisConnection<String, String> connection = redisClient.connect();
		RedisCommands<String, String> syncCommands = connection.sync();
		
		if (token.equals(syncCommands.get("key")))
			return true;
		else
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

}
