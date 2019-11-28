package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Delete {
	/**
	 * 用于删除数据库表中数据
	 * @param sql  传入的sql语句
	 * @return 返回一个boolean值
	 */
	public static boolean DeleteBlog(String sql) {
		int executeUpdate=0;
		try {
			Connection conn = DbConnection.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			executeUpdate = ps.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
		return executeUpdate>0;
	}
}
