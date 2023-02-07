package com.fdmgroup.ChatProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginAndRegisterController {

	
	
	@GetMapping("/")
	public String goToIndex() {
		return "index";
	}
	
	
}
