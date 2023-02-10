package com.fdmgroup.ChatProject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
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
import com.fdmgroup.ChatProject.repository.ChatUserRepository;
import com.fdmgroup.ChatProject.security.DefaultUniqueUserDetailsService;
import com.fdmgroup.ChatProject.service.ChatUserService;
import com.fdmgroup.ChatProject.service.RoleService;
import com.fdmgroup.ChatProject.service.UniqueUserService;



@Controller
public class LoginAndRegisterController {
		
	@Autowired
	DefaultUniqueUserDetailsService defaultUniqueUserDetailsService;
	@Autowired
	ChatUserService chatUserService;
	
	@Autowired
	private PasswordEncoder encoder;
	@Autowired
	RoleService	roleService;
	
	@GetMapping(value = "/indexChat")
	public String goToIndexChat(ModelMap model, Authentication authentication) {
			
		String name = authentication.getUsername();
		System.out.println(name);
		Optional<UniqueUser> uniqueUserOpt = Optional.of(defaultUniqueUserDetailsService.findByUniqueUserName(name));
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
	
	
	@GetMapping(value = "/editingProfil/{id}")
	public String goToProfileSetting() {
		return "profileSetting";
	}
	
	@PostMapping("/register")
	public String registerSubmit(@ModelAttribute("user")UniqueUser uniqueUser,
						@ModelAttribute("chatuser")ChatUser chatUser, ModelMap model) {
		UniqueUser userFromDatabase = defaultUniqueUserDetailsService.findByUniqueUserEmileAdress(uniqueUser.getName());
		if (userFromDatabase.getEmailAdress().equals(uniqueUser.getEmailAdress())) {
			model.addAttribute("message", "This user name already exists");
			return "register";
		}
		
		uniqueUser.setRole(roleService.findByRoleName("Customer"));
		uniqueUser.setPassword(encoder.encode(uniqueUser.getPassword()));
		defaultUniqueUserDetailsService.saveUniqueUser(uniqueUser);
		
		chatUser.setUser(uniqueUser);
		chatUser.setNickName(chatUser.getNickName());
		chatUserService.save(chatUser);
		//model.addAttribute("chatusers", defaultUniqueUserDetailsService.());
		return "redirect:/login";
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
