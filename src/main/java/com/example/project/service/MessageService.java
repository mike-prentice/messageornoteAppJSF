package com.example.project.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import com.example.project.dao.MessageDAO;
import com.example.project.model.Message;

@Stateless
public class MessageService {

	
	MessageDAO messageDAO = new MessageDAO();

	@PersistenceContext
	private EntityManager entityManager;

	public void create(Message message) {
		entityManager.persist(message);
	}

	public List<Message> list(String user) {
		
		return messageDAO.getMessages(user);
	}

	public List<Message> listUserMessages() {
		return entityManager.createQuery("SELECT messages FROM Message m JOIN users u ON u.id = m.id", Message.class).getResultList();
	}

	public void delete(Long id) {
		Message message = findById(id);
		entityManager.remove(message);
	}

	public void save(Long id, String text) {
		Message message = findById(id);
		message.setText(text);
		entityManager.merge(message);
				
	}

	public Message findById(Long id) {
		Query findOne = entityManager.createQuery("Select m From Message m WHERE m.id = :id");
		findOne.setParameter("id", id);
		return (Message) findOne.getSingleResult();

	}

}
