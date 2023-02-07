package com.fdmgroup.ChatProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginAndRegisterController {
		
	@GetMapping(value = "/")
	public String goToIndex() {
		return "index";
	}
	
	@GetMapping(value="/auth/login")
	public String login() {
		return "login";
	}
	
	
}
