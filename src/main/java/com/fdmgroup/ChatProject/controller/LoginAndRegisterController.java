package com.fdmgroup.ChatProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginAndRegisterController {
		
	@GetMapping(value = "/")
	public String goToIndex() {
		return "indexChat";
	}
	
	@GetMapping(value = "/indexChat")
	public String goToIndexChat() {
		return "indexChat";
	}
	
	@GetMapping(value="/login")
	public String login() {
		return "login";
	}
	
	@GetMapping(value="/register")
	public String goToRegister() {
		return "/register";
	}
	
	@PostMapping(value="/register")
	public String actuallRegister() {
		
		//TODO registration
		
		return "/indexChat";
	}

}
