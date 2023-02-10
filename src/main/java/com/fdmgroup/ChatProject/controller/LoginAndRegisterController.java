package com.fdmgroup.ChatProject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.security.DefaultUniqueUserDetailsService;
import com.fdmgroup.ChatProject.service.RoleService;

import com.fdmgroup.ChatProject.service.interfaces.IChatUserService;
import com.fdmgroup.ChatProject.service.interfaces.IUniqueUserService;

@Controller
public class LoginAndRegisterController {

	@Autowired
	DefaultUniqueUserDetailsService defaultUniqueUserDetailsService;

	@Autowired
	IChatUserService chatUserService;

	@Autowired
	IUniqueUserService uniqueUserService;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	RoleService roleService;


	@GetMapping(value = "/")
	public String goToIndex(ModelMap model,Authentication authentication) {
		String name = authentication.getName();
		Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);
		
		if(uniqueUserOpt.isPresent()) {
		Optional<ChatUser> chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());		
		chatUserOpt.ifPresent((chatUser)-> model.addAttribute("currentUser",chatUser));
		}
		return "indexChat";
	}
	
	@GetMapping(value = "/indexChat")
	public String goToIndexChat(ModelMap model, Authentication authentication) {	
		
			String name = authentication.getName();
			Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);
			
			if(uniqueUserOpt.isPresent()) {
			Optional<ChatUser> chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());		
			chatUserOpt.ifPresent((chatUser)-> model.addAttribute("currentUser",chatUser));
			}
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
	public String registerSubmit(@ModelAttribute("user") UniqueUser user, ModelMap model) {
		UniqueUser userFromDatabase = defaultUniqueUserDetailsService.findByUniqueUserEmile(user.getName());
		if (userFromDatabase.getEmailAdress().equals(user.getEmailAdress())) {
			model.addAttribute("message", "This user name already exists");
			return "register";
		}

		user.setRole(roleService.findByRoleName("Customer"));
		user.setPassword(encoder.encode(user.getPassword()));
		defaultUniqueUserDetailsService.saveUniqueUser(user);
		// model.addAttribute("chatusers", defaultUniqueUserDetailsService.());
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
