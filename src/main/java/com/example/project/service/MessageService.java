package com.example.project.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;

import com.example.project.dao.MessageDAO;
import com.example.project.model.Message;
import com.example.project.model.SessionUtils;

@Stateless
public class MessageService {

	List<Message> list = new ArrayList<>();
	MessageDAO messageDAO = new MessageDAO();

	@PostConstruct
	public void init() {
		list = messageDAO.getMessages(SessionUtils.getUserName());
	}

	public void create(String user, String text) {
		messageDAO.saveMessage(user, text);
	}

	public void delete(Long id) {
		messageDAO.deleteMessage(id);
	}

	public void updateMessage(Long id, String text) {
		messageDAO.updateMessage(id, text);

	}
	
	public List<Message> getList() {
		return list;
	}

	public void setList(List<Message> list) {
		this.list = list;
	}

}
