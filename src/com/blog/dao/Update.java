package com.blog.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Update {

	/**
	 * 用于更新修改博客数据
	 * @param sql  传入的sql语句
	 * @return 返回一个boolean值
	 */
	public static boolean UpdateBlog(String sql){
		int executeQuery = 0;
		try {
			Connection conn = DbConnection.getConnection();
			Statement stat = conn.createStatement();
			executeQuery = stat.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return executeQuery>0;
	}
}

