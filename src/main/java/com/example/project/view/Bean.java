package com.example.project.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import com.example.project.model.Message;
import com.example.project.service.MessageService;

@Named("bean")
@ViewScoped
public class Bean implements Serializable {

	private Message message;
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
		messages = messageService.getList();
		message = new Message();
		
		sortBy = new ArrayList<>();
		sortBy.add(SortMeta.builder().field("id").order(SortOrder.ASCENDING).build());

		sortBy.add(SortMeta.builder().field("text").order(SortOrder.ASCENDING).priority(1).build());
	}

	public String submit(String userName, String text) {
		messageService.create(text, userName);
		FacesContext.getCurrentInstance().addMessage("inputForm:inputMessage", new FacesMessage("Message Added!"));
		return "/test.xhtml?faces-redirect=true";
	}

	public void save(Long id, String text) {
		messageService.updateMessage(id, text);
		PrimeFaces.current().ajax().update("tableForm:table");
	}

	public void deleteMessage() {
		messageService.delete(this.selectedMessage.getId());
		this.messages.remove(this.selectedMessage);
		this.selectedMessage = null;
		PrimeFaces.current().ajax().update("tableForm:table");
	}

	public List<SortMeta> getSortBy() {
		return sortBy;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public Message getSelectedMessage() {
		return selectedMessage;
	}

	public void setSelectedMessage(Message selectedMessage) {
		this.selectedMessage = selectedMessage;
	}

	public List<Message> getMessages() {
		return messages;
	}

}
