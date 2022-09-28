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
	List<Message> messages = new ArrayList<>();

	public boolean saveMessage(String user, String text) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("INSERT INTO Messages (userName, message)" + "values (?, ?)");
			ps.setString(1, user);
			ps.setString(2, text);

			ps.execute();

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
		messages.clear();

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("Select * from messages where userName = ?");
			ps.setString(1, user);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Message message = new Message();
				message.setText(rs.getString("message"));
				message.setId(rs.getLong("messageId"));
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

	public boolean deleteMessage(Long id) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("DELETE FROM Messages WHERE messageId = ?");
			ps.setLong(1, id);

			ps.execute();

		} catch (SQLException ex) {
			System.out.println("Delete error -->" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
		return false;
	}

	public boolean updateMessage(Long id, String text) {
		Connection con = null;
		PreparedStatement ps = null;

		try {
			con = DataConnect.getConnection();
			ps = con.prepareStatement("UPDATE Messages SET message = ? WHERE messageId = ?");
			ps.setString(1, text);
			ps.setLong(2, id);

			ps.execute();

		} catch (SQLException ex) {
			System.out.println("Update error -->" + ex.getMessage());
			return false;
		} finally {
			DataConnect.close(con);
		}
		return false;
	}
}
