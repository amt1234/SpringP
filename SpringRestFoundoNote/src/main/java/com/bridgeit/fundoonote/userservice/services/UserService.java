package com.bridgeit.fundoonote.userservice.services;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import com.bridgeit.fundoonote.userservice.model.EmailInfo;
import com.bridgeit.fundoonote.userservice.model.RegistrationDTO;
import com.bridgeit.fundoonote.userservice.model.UserProfile;

public interface UserService {
	public boolean registationSave(RegistrationDTO registrationDTO, String link);

	public String check(String userEmail, String password);

	public boolean getUserTokenVerify(String token);

	public boolean forgotUserPassword(String token, String password);

	public boolean forgotUserPasswordlink(String token, HttpServletResponse response);

	public boolean resetUserPassword(EmailInfo emailInfo, String link);

	public UserProfile updateUser(String token, UserProfile userProfile);

	public UserProfile userInformation(String token);

	public List<UserProfile> userList();

	public boolean addCollaboratorOnNote(long id, UserProfile userProfile);

	public boolean removeCollaborator(long id, UserProfile userProfile);
}
