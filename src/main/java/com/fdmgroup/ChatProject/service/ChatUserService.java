package com.fdmgroup.ChatProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.repository.ChatUserRepository;
import com.fdmgroup.ChatProject.service.interfaces.IChatUserService;

@Service
public class ChatUserService implements IChatUserService{

	@Autowired 
	ChatUserRepository chatUserRepository;
	
	@Override
	public ChatUser findById(long userID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void save(ChatUser chatUser) {
		chatUserRepository.save(chatUser);
		
	
		
	}

}
