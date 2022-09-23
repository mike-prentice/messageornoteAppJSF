package com.example.project.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.example.project.model.Message;
import com.example.project.util.DataConnect;

@Stateless
public class MessageDAO {
	public boolean saveMessage(String user, String text) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("INSERT INTO Messages (userName, message)" + "values (?, ?)");
			ps.setString(1, user);
			ps.setString(2, text);

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				// result found, means valid inputs
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

	public List<Message> getMessages(String user) {
		Connection con = null;
		PreparedStatement ps = null;
		List<Message> messages = new ArrayList<>();

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("SELECT * FROM messages WHERE userName = ?");
			ps.setString(1, user);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Message message = new Message();
				message.setText(rs.getString("message"));
				messages.add(message);

			}
		} catch (Exception e) {
			System.out.println(e.toString());
		} finally {
			try {
				con.close();
			} catch (SQLException er) {
				System.out.println(er);
			}
		}
		return messages;
	}
}
