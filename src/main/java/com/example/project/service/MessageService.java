package com.example.project.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import com.example.project.model.Message;

@Stateless
public class MessageService {

	@PersistenceContext
	private EntityManager entityManager;

	public void create(Message message) {
		entityManager.persist(message);
	}

	public List<Message> list() {
		return entityManager.createQuery("FROM Message m", Message.class).getResultList();
	}

	public void delete(Long id) {
		System.out.println(id);
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
