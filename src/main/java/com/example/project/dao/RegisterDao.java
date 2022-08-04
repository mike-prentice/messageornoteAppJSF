package com.example.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.project.util.DataConnect;

public class RegisterDao {
	
	public static boolean checkUName(String user) {
		System.out.println(user);
		Connection con = null;
		PreparedStatement ps = null;
		
		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("SELECT * FROM Users where uname = ?");
			ps.setString(1, user);
			
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				//result found, means valid inputs
				return true;
			}
		} catch (SQLException ex) {
			System.out.println("Login error -->" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
		return false;
	}
	
	
	
	public static boolean register(String user, String password) {
		System.out.println(user);
		System.out.println(password);
		if(!checkUName(user)) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("INSERT INTO Users (uname, password)" + "values (?, ?)");
			ps.setString(1, user);
			ps.setString(2, password);
ps.execute();
		
		} catch (SQLException ex) {
			System.out.println("Registration failed" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
		
	} else { System.out.println("Didn't work...");}
		return false;
	} 
}
