package com.fdmgroup.ChatProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FriendListController {

	@PostMapping("/addNewFriend")
	public String addNewFried(ModelMap model) {
		
		model.addAttribute("friendMessage","new Friend");
		return "indexChat";
	}
	
	
}
