package com.bridgeit.fundoonote.userservice.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgeit.fundoonote.configration.FundooNoteConfigration;
import com.bridgeit.fundoonote.noteservice.dao.INoteDao;
import com.bridgeit.fundoonote.noteservice.model.Note;
import com.bridgeit.fundoonote.userservice.dao.UserDao;
import com.bridgeit.fundoonote.userservice.model.EmailInfo;
import com.bridgeit.fundoonote.userservice.model.RegistrationDTO;
import com.bridgeit.fundoonote.userservice.model.User;
import com.bridgeit.fundoonote.userservice.model.UserProfile;
import com.bridgeit.fundoonote.userservice.utility.IJwtProgram;
import com.bridgeit.fundoonote.userservice.utility.RedisConfigration;

import org.hibernate.SessionFactory;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	@Autowired
	private UserDao userDao;
	@Autowired
	private INoteDao iNoteDao;

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
			return token;
		} else
			throw new RuntimeException("Invalid username");
	}

	@Override
	public boolean forgotUserPassword(String token, String password) {
		LOGGER.info("forgot password in service");
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
	public boolean forgotUserPasswordlink(String token, HttpServletResponse response) {
		LOGGER.info("forgot password in service");
		long id = iJwtProgram.parseJWT(token);

		String synCommand = RedisConfigration.redisUtil().get(String.valueOf(id));

		System.out.println("token in verified user " + token);

		if (token.equals(synCommand)) {
			try {
				response.sendRedirect("http://127.0.0.1:8081/#!/reset?token=" + token);
			} catch (IOException e) {
				System.out.println(e);
			}
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

	@Override
	public UserProfile updateUser(String token, UserProfile userProfile) {

		long id = iJwtProgram.parseJWT(token);

		User user = userDao.checkId(id);
		user.setUserProfileImage(userProfile.getUserProfileImage());
		if (userDao.updateUser(user)) {
			userProfile.setUserProfileImage(user.getUserProfileImage());
			return userProfile;
		} else
			return null;
	}

	@Override
	public UserProfile userInformation(String token) {
		UserProfile userProfile = new UserProfile();
		long id = iJwtProgram.parseJWT(token);
		User user = userDao.checkId(id);
		userProfile.setUserName(user.getUserName());
		userProfile.setUserEmail(user.getUserEmail());
		userProfile.setUserProfileImage(user.getUserProfileImage());
		return userProfile;
	}

	@Override
	public List<UserProfile> userList() {
		List<UserProfile> userProfiles = new ArrayList<>();
		List<User> user = userDao.userList();
		for (User user2 : user) {
			UserProfile userProfile = new UserProfile();
			userProfile.setUserName(user2.getUserName());
			userProfile.setUserEmail(user2.getUserEmail());
			userProfile.setUserProfileImage(user2.getUserProfileImage());

			userProfiles.add(userProfile);
		}
		return userProfiles;
	}

	@Override
	public boolean addCollaboratorOnNote(long id, UserProfile userProfile) {
		Note note = iNoteDao.checkNoteId(id);
		System.out.println("user profile" + userProfile);
		User user = userDao.checkEmail(userProfile.getUserEmail());

		Set<Note> notes = new HashSet<Note>();
		Set<User> users = new HashSet<User>();

		notes = user.getNoteset();
		notes.add(note);
		user.setNoteset(notes);
		userDao.updateUser(user);

		users = note.getUserset();
		users.add(user);
		note.setUserset(users);
		iNoteDao.updateNote(note);

		return true;
	}

	@Override
	public boolean removeCollaborator(long id, UserProfile userProfile) {
		Note note = iNoteDao.checkNoteId(id);
		User user = userDao.checkEmail(userProfile.getUserEmail());

		Set<Note> notes = new HashSet<Note>();
		Set<User> users = new HashSet<User>();

		notes=user.getNoteset();
		for(Note note2:notes) {
			if(note2.getNoteId()==id) {
				System.out.println("note id :"+note2.getNoteId());
				notes.remove(note2);
				break;
			}
		}
		user.setNoteset(notes);
		
		users=note.getUserset();
		for(User user2:users) {
			if(user2.getUserId()==user.getUserId()) {
				System.out.println("note id .....: "+user2.getUserId());
				users.remove(user2);
				break;
			}
		}
		note.setUserset(users);
		userDao.updateUser(user);
		iNoteDao.updateNote(note);
		return true;
	}
}