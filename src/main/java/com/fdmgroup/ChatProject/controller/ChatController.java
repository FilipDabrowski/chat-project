package com.fdmgroup.ChatProject.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.service.interfaces.IChatService;
import com.fdmgroup.ChatProject.service.interfaces.IChatUserService;
import com.fdmgroup.ChatProject.service.interfaces.IMessageService;
import com.fdmgroup.ChatProject.service.interfaces.IUniqueUserService;

@Controller
public class ChatController {

	@Autowired
	private IUniqueUserService uniqueUserService;
	
	@Autowired
	private IChatUserService chatUserService;

	@Autowired
	private IChatService chatService;

	@Autowired
	private IMessageService messageService;

	@PostMapping("/goToChatWith/{id}")
	public String displayChat(ModelMap model, @PathVariable("id") long friendId,
			@RequestParam("currentUserID") long chatUserID) {

		Optional<ChatUser> currChatUser = chatUserService.findById(chatUserID);
		Optional<ChatUser> friend = chatUserService.findById(friendId);

		if (friend.isPresent() && currChatUser.isPresent()) {

			List<Chat> chatList = chatService.findAll();

			for (Chat chat : chatList) {

				if (chat.getChattingUsers().size() == 2 && chat.getChattingUsers().contains(friend.get())
						&& chat.getChattingUsers().contains(currChatUser.get())) {
					model.addAttribute("selectedChat", chat);
					model.addAttribute("chatMessages", messageService.findByChat(chat));
					break;
				}
			}

		}
		
		
		
		model.addAttribute("currentUser", currChatUser.get());

		return "indexChat";
	}

	@PostMapping("/goToGroupChat/{id}")
	public String displayGroupChat(ModelMap model, @PathVariable("id") long chatId,
			@RequestParam("currentUserID") long chatUserID) {

		Optional<ChatUser> currChatUser = chatUserService.findById(chatUserID);
		Optional<Chat> chatOpt = chatService.findById(chatId);

	
		chatOpt.ifPresent(chat -> {
			model.addAttribute("chatMessages", messageService.findByChat(chat));
			model.addAttribute("selectedChat", chat);
			});
		model.addAttribute("currentUser", currChatUser.get());

		
		
		return "indexChat";
	}

	
	@PostMapping("/createNewGroupChat")
	public String createNewGroupChat(ModelMap model,Authentication authentication) {
		String name = authentication.getName();
		Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);
		
		if(uniqueUserOpt.isPresent()) {
		Optional<ChatUser> chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());		
		chatUserOpt.ifPresent((chatUser)-> model.addAttribute("currentUser",chatUser));
		}
		return "createChat";
	}
	
	
	
	@PostMapping("/createChat")
	public String createChat(ModelMap model,Authentication authentication,
			@RequestParam("chatName") String chatName,
			@RequestParam("userToAdd") String[] userToAdd) {
		
		String name = authentication.getName();
		Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);
		Optional<ChatUser> chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());		
	
		
		Chat chat = new Chat();
		chat.setChatName(chatName);
		chatService.addUserToChat(chat, chatUserOpt.get());
		

		for(String val: userToAdd) {
				chatService.addUserToChat(chat, chatUserService.findByNickName(val).get());
		}
		chatService.save(chat);
		
		
		chatUserService.addChatToUser(chatUserOpt.get(),chat);
		for(String val: userToAdd) {
				chatUserService.addChatToUser(chatUserService.findByNickName(val).get(),chat);
			}

		
		 model.addAttribute("currentUser",chatUserOpt.get());
		return "indexChat";
	}

}
