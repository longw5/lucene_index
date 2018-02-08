package com.beagle.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.beagle.util.DbUtils;

public class Demo {

	public static void main(String[] args) {
		
		System.out.println(Integer.MAX_VALUE);
	}
	
	public static void main1(String[] args) throws Exception {

		Connection conn = DbUtils.getConnection();
		
		PreparedStatement ps = conn.prepareStatement("select * from actor");
		
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			String clume1 = rs.getString(1);
			String clume2 = rs.getString(2);
			System.out.println(clume1 + ":" + clume2);
		}

	}
}
