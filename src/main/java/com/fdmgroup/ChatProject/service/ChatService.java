package com.fdmgroup.ChatProject.service;

import org.springframework.stereotype.Service;

import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.service.interfaces.IChatService;

@Service
public class ChatService implements IChatService {

	public void addUserToChat(Chat chat,ChatUser user) {
		chat.getChattingUsers().add(user);
	}

	@Override
	public Chat findById(long chatID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Chat chat) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
