package com.bridgeit.fundoonote.dao;


import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoonote.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	SessionFactory sessionFacetory;

	@Override
	public long save(User user) {
		LOGGER.info("start save method of dao");
		return (long) sessionFacetory.getCurrentSession().save(user);
	}

	@Override
	public boolean login(long userId) {
		return false;
	}

	@SuppressWarnings("deprecation")
	@Override
	public User checkEmail(String userEmail) {
		LOGGER.info("start checkEmail method of dao");
		Criteria criteria=sessionFacetory.getCurrentSession().createCriteria(User.class)
				.add(Restrictions.eq("userEmail", userEmail));
		User user=(User)criteria.uniqueResult();
		return user;
	}
	
	/*public User passwordInfo(User user)
	{
		System.out.println("user.."+user.getUserEmail());
		return user.getUserEmail();
		
	}*/
	
	@Override
	 public boolean updateUser(boolean isActiveUser) {
         
      /*Query query = sessionFacetory.getCurrentSession().createQuery("update FoundooTable set isActiveUser =:isActive where isActiveUser = :isNotActive");
      query.setBoolean("isActive", true); 
      query.setBoolean("isNotActive", false);  
      int result = query.executeUpdate();
      return result;*/
		//String query="update FoundooTable set isActiveUser=1 where isActiveUser=0;";
		sessionFacetory.getCurrentSession().update(isActiveUser);
		return true;
     }
	/*@Override
	public boolean checkPassword(String password) {
		if (BCrypt.checkpw(password, password))
			return true;
		else
			return false;
	}*/


}
