package com.example.project.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import com.example.project.dao.MessageDAO;
import com.example.project.model.Message;
import com.example.project.model.SessionUtils;
import com.example.project.service.MessageService;

@Named
@ViewScoped
public class Bean implements Serializable {

	private Message message = new Message();
	private List<Message> messages;
	private List<SortMeta> sortBy;
	private Message selectedMessage;
	

	@Inject
	private MessageService messageService;
	private MessageDAO messageDao;
	
	
	

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	public void setMessageDao(MessageDAO messageDao) {
		this.messageDao = messageDao;
	}


	@PostConstruct
	public void init() {
		messages = messageService.list(SessionUtils.getUserName());
		message = new Message();
		//System.out.println(messages.get(0).getText());

		sortBy = new ArrayList<>();
		sortBy.add(SortMeta.builder().field("id").order(SortOrder.ASCENDING).build());

		sortBy.add(SortMeta.builder().field("text").order(SortOrder.ASCENDING).priority(1).build());
	}

	public String submit(String userName, String text) {
		System.out.println(userName);
		System.out.println(text);
		messageService.create(text, userName);
		//messages.add(message);
		//message = new Message();
		FacesContext.getCurrentInstance().addMessage("inputForm:inputMessage", new FacesMessage("Message Added!"));
		return "/test.xhtml?faces-redirect=true";
	}

	public void save(Long id, String text) {
		messageService.save(id, text);
		PrimeFaces.current().ajax().update("tableForm:table");
	}

	public void deleteMessage() {
		this.messages.remove(this.selectedMessage);
		this.selectedMessage = null;
		PrimeFaces.current().ajax().update("tableForm:table");
	}

	public List<Message> getMessages() {
		return messages;
	}

	public Message getSelectedMessage() {
		return selectedMessage;
	}

	public void setSelectedMessage(Message selectedMessage) {
		this.selectedMessage = selectedMessage;
	}

	public List<SortMeta> getSortBy() {
		return sortBy;
	}

	public String getSessionId() {
		String id = SessionUtils.getUserId();
		return id;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Message getMessage() {
		return message;
	}

}
