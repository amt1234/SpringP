package com.bridgeit.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bridgeit.model.User; 
@Repository
public class UserDao implements IUserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
*/
	public int addUser(User user) {
		
		String sql = "insert into SpringTable(username,email,password,mobileNo,dob) values(?,?,?,?,?)";
		Object[] args = new Object[] { user.getUsername(), user.getEmail(), user.getPassword(), user.getMobileNo(),
				user.getDob() };
		return jdbcTemplate.update(sql, args);
	}

}
