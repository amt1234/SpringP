package com.bridgeit.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bridgeit.model.User;

@Component
@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	UserMapper userMapper;

	public void setUserMapper(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	/*
	 * @Autowired public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	 * this.jdbcTemplate = jdbcTemplate; }
	 */

	// Display all user
	@Override
	public List<User> listAllUser(User user) {
		String sql = "select * from SpringTable where email=?";
		Object[] args = new Object[] { user.getEmail() };
		return jdbcTemplate.query(sql, new UserMapper());
	}

	// select perticular user by id
	@Override
	public boolean findUser(User user) {

		String sql = "select * from SpringTable where email=?";
		Object[] args = new Object[] { user.getEmail() };
		List<User> list = jdbcTemplate.query(sql, args, new UserMapper());

		System.out.println("email db" + user.getEmail());
		System.out.println("list data " + list);

		if (list.isEmpty())
			return false;
		else
			return true;
	}

	// Add new user information
	@Override
	public int addUser(User user) {
		String sql = "insert into SpringTable(username,email,password,mobileNo,dob) values(?,?,?,?,?)";
		Object[] args = new Object[] { user.getUsername(), user.getEmail(), user.getPassword(), user.getMobileNo(),
				user.getDob() };
		return jdbcTemplate.update(sql, args);
	}

	// Update user dob and email by id
	@Override
	public int updateUser(User user) {
		String sql = "update SpringTable set dob=? and email=? where Userid=?";
		return jdbcTemplate.update(sql);

	}

	// delete perticular user by id
	@Override
	public int deleteUser(User user) {
		String sql = "delete from SpringTable where Userid=?";
		return jdbcTemplate.update(sql);

	}

	class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			User user = new User();
			user.setUsername(resultSet.getString("username"));
			user.setEmail(resultSet.getString("email"));
			user.setMobileNo(resultSet.getLong("mobileNo"));
			user.setDob(resultSet.getString("dob"));
			return user;
		}

	}

}
