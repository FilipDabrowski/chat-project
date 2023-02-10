package com.fdmgroup.ChatProject.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.repository.ChatRepository;
import com.fdmgroup.ChatProject.service.interfaces.IChatService;

@Service
public class ChatService implements IChatService {
	
	@Autowired
	private ChatRepository chatRepository;

	public void addUserToChat(Chat chat,ChatUser user) {
		chat.getChattingUsers().add(user);
		chatRepository.save(chat);
	}

	@Override
	public Optional<Chat> findById(long chatID) {
		return chatRepository.findById(chatID);	 
	}

	@Override
	public void save(Chat chat) {
		List<Chat> allChats = findAll();
		if(!allChats.contains(chat)) {
		chatRepository.save(chat);
		}
	}

	@Override
	public List<Chat> findAll() {
		return chatRepository.findAll();
	}
	
	
	
}
