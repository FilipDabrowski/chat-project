package com.fdmgroup.ChatProject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.security.DefaultUniqueUserDetailsService;
import com.fdmgroup.ChatProject.service.RoleService;
import com.fdmgroup.ChatProject.service.UniqueUserService;



@Controller
public class LoginAndRegisterController {
		
	@Autowired
	DefaultUniqueUserDetailsService defaultUniqueUserDetailsService;
	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	RoleService	roleService;
	
//	@GetMapping(value = "/")
//	public String goToIndex() {
//		return "indexChat";
//	}
	
	@GetMapping(value = "/indexChat")
	public String goToIndexChat() {
		return "indexChat";
	}
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@PostMapping("/register")
	public String registerSubmit(@ModelAttribute("user")UniqueUser user, ModelMap model) {
		UniqueUser userFromDatabase = defaultUniqueUserDetailsService.findByUniqueUserEmile(user.getName());
		if (userFromDatabase.getEmailAdress().equals(user.getEmailAdress())) {
			model.addAttribute("message", "This user name already exists");
			return "register";
		}
		
		user.setRole(roleService.findByRoleName("Customer"));
		user.setPassword(encoder.encode(user.getPassword()));
		defaultUniqueUserDetailsService.saveUniqueUser(user);
		//model.addAttribute("chatusers", defaultUniqueUserDetailsService.());
		return "index";
	}
	
	@ExceptionHandler(UsernameNotFoundException.class)
	public ModelAndView handleUsernameNotFoundException(UsernameNotFoundException ex) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("notFound");
		mav.addObject("type", "user");
		mav.addObject("message", ex.getMessage());
		return mav;
	}
	
	
}
