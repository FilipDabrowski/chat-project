package com.fdmgroup.ChatProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GroupChatController {

	@PostMapping("/createNewGroupChat")
	public String createNewGroupChat() {
		
		return "createChat";
	}
	
}
