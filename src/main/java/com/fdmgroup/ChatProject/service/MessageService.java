package com.fdmgroup.ChatProject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.Message;
import com.fdmgroup.ChatProject.repository.MessageRepository;
import com.fdmgroup.ChatProject.service.interfaces.IMessageService;
@Service
public class MessageService implements IMessageService {

	@Autowired
	public MessageRepository messageRepository;
	
	@Override
	public void save(Message message) {		
		messageRepository.save(message);
	}

	@Override
	public List<Message> findByChat(Chat chat) {

		return messageRepository.findByChat(chat);
	}

}
