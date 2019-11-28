package com.blog.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static final String DRIVER = "com.mysql.cj.jdbc.Driver"; 
	public static final String URL = "jdbc:mysql://localhost:3306/db_blog?useUnicode=true&characterEncoding=UTF-8"; 
	public static final String USER = "root"; 
	public static final String PASSWORD = "123456"; 
	public static  Connection conn = null;
	public static Connection getConnection() {
		
		try {
			if(conn!=null&&!conn.isClosed()) {
				return conn;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			Class.forName(DRIVER);
			 conn = DriverManager.getConnection(URL,USER,PASSWORD);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
}
