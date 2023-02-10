package com.fdmgroup.ChatProject.controller;

import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.Message;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.service.interfaces.IChatService;
import com.fdmgroup.ChatProject.service.interfaces.IChatUserService;
import com.fdmgroup.ChatProject.service.interfaces.IMessageService;
import com.fdmgroup.ChatProject.service.interfaces.IUniqueUserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {

	@Autowired
	IChatService chatService;

	@Autowired
	IChatUserService chatUserService;

	@Autowired
	IUniqueUserService uniqueUserService;
	
	@Autowired
	IMessageService messageService;
	
	
	@PostMapping("/sendMessage")
	public String sendMessageToChat(ModelMap model, Authentication authentication,
			@RequestParam("message") String message,
			@RequestParam(required = false, name="currentChatID") String chatID
			
			){
		if(chatID.isBlank()) {chatID="0";}
		
		Optional<ChatUser> chatUserOpt = Optional.empty();
		String name = authentication.getName();
		Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);
		if (uniqueUserOpt.isPresent()) {
			chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());
		}
		Optional<Chat> currChatOpt = chatService.findById(Long.parseLong(chatID));
		
		
		if(currChatOpt.isPresent()) {
		Message sendMessage = new Message(message, chatUserOpt.get(), currChatOpt.get());
		messageService.save(sendMessage);
		List<Message> chatMessages = messageService.findByChat(currChatOpt.get());
		model.addAttribute("chatMessages", chatMessages);		
		model.addAttribute("selectedChat", currChatOpt.get());
		}
		
		model.addAttribute("currentUser", chatUserOpt.get());
		
		
		return "indexChat";
	}


	
	
	
	
	
	
	
	
	
	
	
}
