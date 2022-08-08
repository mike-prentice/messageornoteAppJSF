package com.example.project.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import com.example.project.model.Message;
import com.example.project.service.MessageService;

@Named
@RequestScoped
public class Bean {

	private Message message = new Message();
	private List<Message> messages;
	private List<SortMeta> sortBy;
	private Message selectedMessage;
	
	@Inject
	private MessageService messageService;

	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@PostConstruct
	public void init() {
		messages = messageService.list();
		
		sortBy = new ArrayList<>();
		sortBy.add(SortMeta.builder().field("id").order(SortOrder.ASCENDING).build());

		sortBy.add(SortMeta.builder().field("text").order(SortOrder.ASCENDING).priority(1).build());
	}

	public String submit() {
		messageService.create(message);
		messages.add(message);
		message = new Message();
		FacesContext.getCurrentInstance().addMessage("inputForm:inputMessage", new FacesMessage("Message Added!"));
		return "/test.xhtml?faces-redirect=true";
	}
	
	public String save(Long id, String text) {
		messageService.save(id, text);
		return "/test.xhtml?faces-redirect=true";
	}

	public String deleteMessage(Long id) {
		messageService.delete(id);
		return "/test.xhtml?faces-redirect=true";
	}

	public Message getMessage() {
		return message;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public Message getSelectedMessage() {
		return selectedMessage;
	}

	public void setSelectedMessage(Message message) {
		this.selectedMessage = message;
	}

	public List<SortMeta> getSortBy() {
		return sortBy;
	}

	//create selectEditText method (String return)
	//if null, return ""
	// else return selectedMessage.text
	
	
}
