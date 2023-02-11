package com.fdmgroup.ChatProject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
public class ChatUserProfileController {

	@Autowired
	IChatService chatService;

	@Autowired
	IChatUserService chatUserService;

	@Autowired
	IUniqueUserService uniqueUserService;

	@Autowired
	IMessageService messageService;
	
	@PostMapping("/addNewFriend")
	private String addUserToFriendlist(ModelMap model, Authentication authentication,
			@RequestParam("nickName") String nickName,
			@RequestParam(required = false, name="currentChatID") String chatID
			) {
		if(chatID.isBlank()) {chatID="0";}
		
		Optional<ChatUser> chatUserOpt = Optional.empty();
		String name = authentication.getName();

		Optional<Chat> currChatOpt = chatService.findById(Long.parseLong(chatID));
		
		Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);
		if (uniqueUserOpt.isPresent()) {
			chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());
		}
		Optional<ChatUser> friendChatUserOpt = chatUserService.findByNickName(nickName);

		if (chatUserOpt.isPresent() && friendChatUserOpt.isPresent()) {
			ChatUser currChatUser = chatUserOpt.get();
			ChatUser friendChatUser = friendChatUserOpt.get();

			
			
			
			if (!currChatUser.equals(friendChatUser) && !currChatUser.getFriendList().contains(friendChatUser)) {
				chatUserService.addFrindToList(currChatUser, friendChatUser);
				model.addAttribute("friendMessage", nickName);

				Chat chatWithFriend = new Chat(currChatUser.getId() + "_" + friendChatUser.getId(), currChatUser,
						friendChatUser);
				chatService.save(chatWithFriend);

				chatUserService.addChatToUser(currChatUser, chatWithFriend);
				chatUserService.addChatToUser(friendChatUser, chatWithFriend);
			}
		}
		chatUserOpt.ifPresent((chatUser) -> model.addAttribute("currentUser", chatUser));
		currChatOpt.ifPresent(chat->{
				model.addAttribute("chatMessages", messageService.findByChat(chat));
				model.addAttribute("selectedChat",chat);
			});
		
		return "indexChat";
	}

	
	
	
	
	
	
}
