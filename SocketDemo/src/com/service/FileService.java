package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.entity.File;
import com.util.DBUtil;

public class FileService {

	public void insert(File file) throws SQLException {
		
		Connection connection = DBUtil.getConnection(); 
		
		String sql = "insert into db_file(name,content)" + " values (?,?)";
		PreparedStatement statement = connection.prepareStatement(sql); 
		
		statement.setString(1, file.getFname());
		
		statement.setBytes(2, file.getFcontent());
	
		statement.executeUpdate();
	}
	
}
