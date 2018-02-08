package com.beagle.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtils {

	private static String url = "jdbc:mysql://localhost:3306/sakila?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8";
	private static String username = "root";
	private static String passwd = "";
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
