package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.entity.User;
import com.util.DBUtil;



public class UserService {
	
	public void insert(User user) throws SQLException{

		Connection connection = DBUtil.getConnection();

		String sql = "insert into db_user(name,password)" + " values (?,?)";
		PreparedStatement statement = connection.prepareStatement(sql); 
		statement.setString(1, user.getUsername());
		statement.setString(2, user.getPassword()); 
		
                statement.executeUpdate();
	}
	
	public List<User> query() throws SQLException{
		List<User> users = new ArrayList<User>();
	
		Connection connection = DBUtil.getConnection();
	
		String sql = "select * from db_user";
		PreparedStatement statement = connection.prepareStatement(sql); 
		
		ResultSet set = statement.executeQuery();
		
		while (set.next()) {
			User user = new User(); 
			user.setId(set.getInt("id"));
			user.setUsername(set.getString("name"));
			user.setPassword(set.getString("password")); 
			users.add(user); 
			}
		return users;
	}

}
