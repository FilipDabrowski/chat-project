package com.fdmgroup.ChatProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.service.interfaces.IChatService;
import com.fdmgroup.ChatProject.service.interfaces.IChatUserService;

@Controller
public class ChatController {

	@Autowired
	IChatUserService chatUserService;
	
	@Autowired
	IChatService chatService;
	
	
	
	@PostMapping("/addUser/{userID}/ToChat/{ChatID}")
	public String add(ModelMap model, @PathVariable("userID") long userID, @PathVariable("chatID") long chatID) {
		
		ChatUser user = chatUserService.findById(userID);
		Chat chat = chatService.findById(chatID);
		
		chatService.addUserToChat(chat,user); 
		chatService.save(chat);
		
		
		return "somePage";
		
		
	}
	
	
	
}
