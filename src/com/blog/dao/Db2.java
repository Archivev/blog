package com.blog.dao;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blog.entity.Blog;

public class Db2 {
//	private  int age;
	public static void main(String[] args) {

		String sql = "select  * from t_blog";
		List<Blog> select = select1(sql, Blog.class);
		for (Blog map : select) {
			System.out.println(map);
		}
//		String user = "坤旭";
//		String pwd = "123456";
//		System.out.println(user);
//		System.out.println(pwd);
//		String sqlInsert = "insert into t_user (userName,pwd) values('" + user + "','" + pwd + "')";
//		boolean insert = insert(sqlInsert);
//		if(insert) {
//			System.out.println("插入成功");
//		}else {
//			System.out.println("插入失败");
//		}
//		String sql = "select count(*) from t_user";
//		int count = getCount(sql);
//		System.out.println(count);
	}

	public static boolean insert(String sql) {
		int executeUpdate = 0;
		try {
			// 创建连接
			Connection conn = DbConnection.getConnection();
			// 创建一个"小手"
			Statement statement = conn.createStatement();
			// 执行插入语句
			executeUpdate = statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return executeUpdate > 0;
	}

	/**
	 * 
	 * @param sql  查询的sql select * from user where userName='zhangsan'
	 * @param args 查询的列名 数组
	 * @return 对应的数据
	 */
	public static List<Map<String, String>> select(String sql, String[] args) {
		List<Map<String, String>> list = new ArrayList<>();
		try {
			Connection conn = DbConnection.getConnection();
			// 创建一个"小手"
			Statement statement = conn.createStatement();
			// 通过我们这个小手执行我们的sql拿回数据
			ResultSet resultSet = statement.executeQuery(sql);
			// 遍历结果集
			while (resultSet.next()) {
				Map<String, String> map = new HashMap<>();
				for (String str : args) {
					map.put(str, resultSet.getString(str));
				}
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @param      <T>
	 * @param sql  查询的sql select * from user where userName='zhangsan'
	 * @param args 查询的列名 数组
	 * @return 对应的数据
	 */
	public static <T> List<T> select(String sql, Class<T> cl) {
		List<T> list = new ArrayList<>();
		try {
			Connection conn = DbConnection.getConnection();
			// 创建一个"小手"
			Statement statement = conn.createStatement();
			// 通过我们这个小手执行我们的sql拿回数据
			ResultSet resultSet = statement.executeQuery(sql);
			// 遍历结果集
			while (resultSet.next()) {
				T t = cl.newInstance();
//				String [] strs= {};
				Field[] declaredFields = cl.getDeclaredFields();
				AccessibleObject.setAccessible(declaredFields, true);
				for (Field field : declaredFields) {
					field.set(t, resultSet.getObject(field.getName()));
				}
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * @param      <T>
	 * @param sql  查询的sql select * from user where userName='zhangsan'
	 * @param args 查询的列名 数组
	 * @return 对应的数据
	 */
	public static <T> List<T> select1(String sql, Class<T> cl) {
		List<T> list = new ArrayList<>();
		try {
			Connection conn = DbConnection.getConnection();
			// 创建一个"小手"
			Statement statement = conn.createStatement();
			// 通过我们这个小手执行我们的sql拿回数据
			ResultSet resultSet = statement.executeQuery(sql);
			// 遍历结果集
			while (resultSet.next()) {
				T t = cl.newInstance();
//				String [] strs= {};
//				Field [] declaredFields = cl.getDeclaredFields();
//				AccessibleObject.setAccessible(declaredFields, true);
//				for (Field field : declaredFields) {
//					field.set(t, resultSet.getObject(field.getName()));
//				}
				Method[] declaredMethods = cl.getDeclaredMethods();
				for (Method method : declaredMethods) {
					String name = method.getName();
					if (name.startsWith("set")) {
						String str = name.substring(3);
						method.invoke(t, resultSet.getObject(lowerFirst(str)));
					}
				}
				list.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private static String lowerFirst(String oldStr) {

		char[] chars = oldStr.toCharArray();

		chars[0] += 32;

		return String.valueOf(chars);

	}

	// 一个sql
	// int
	/**
	 * 通过传入一个sql 获得总条数
	 * 
	 * @param sql select count(1) from t_user
	 * @return 总条数
	 * @author yhtgb
	 */
	public static int getCount(String sql) {
		Connection connection = DbConnection.getConnection();
		int total = 0;
		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				total = resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return total;
	}
}
