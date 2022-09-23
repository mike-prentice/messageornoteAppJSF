package com.example.project.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


import com.example.project.dao.MessageDAO;

@Named
@SessionScoped
public class Message implements Serializable {

	MessageDAO messageDAO = new MessageDAO();

	private static final long serialVersionUID = 1L;

	private Long id;
	private String text;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 59 * hash + (this.id != null ? this.id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Message other = (Message) obj;
		if ((this.id == null) ? (other.id != null) : !this.id.equals(other.id)) {
			return false;
		}
		return true;
	}

	public void createMessage(String userName, String text) {
		messageDAO.saveMessage(userName, text);
	}
	
}
