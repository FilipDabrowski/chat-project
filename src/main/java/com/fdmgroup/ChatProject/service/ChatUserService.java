package com.fdmgroup.ChatProject.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.repository.ChatUserRepository;
import com.fdmgroup.ChatProject.service.interfaces.IChatUserService;

@Service
public class ChatUserService implements IChatUserService{

	@Autowired
	private ChatUserRepository chatUserRepository; 

	
	@Override
	public ChatUser findById(long userID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ChatUser chatUser) {
		chatUserRepository.save(chatUser);
	}

	@Override
	public Optional<ChatUser>  findByUser(UniqueUser name) {
		
	List<ChatUser> userList = chatUserRepository.findByUser(name);
	
	if(userList.isEmpty()) {
		return  Optional.empty();
	}
	return Optional.of(userList.get(0));
	}

}
