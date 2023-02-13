package com.fdmgroup.ChatProject.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.Role;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.security.DefaultUniqueUserDetailsService;
import com.fdmgroup.ChatProject.service.ChatService;
import com.fdmgroup.ChatProject.service.ChatUserService;
import com.fdmgroup.ChatProject.service.RoleService;
import com.fdmgroup.ChatProject.service.UniqueUserService;
import com.fdmgroup.ChatProject.service.interfaces.IChatUserService;
import com.fdmgroup.ChatProject.service.interfaces.IUniqueUserService;

@Controller
public class LoginAndRegisterController {

	@Autowired
	DefaultUniqueUserDetailsService defaultUniqueUserDetailsService;

	//@Autowired
	//IChatUserService chatUserService;

	@Autowired
	IUniqueUserService iUniqueUserService;

	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	RoleService roleService;
	@Autowired
	UniqueUserService uniqueUserService;
	@Autowired
	ChatUserService chatUserService;


	@GetMapping(value = "/indexChat")
	public String goToIndexChat(ModelMap model, Authentication authentication) {
			
		String name = authentication.getName();
		System.out.println(name);
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
	public String registerSubmit(@ModelAttribute("user") UniqueUser user, @RequestParam("nickName") String nickName, ModelMap model) {
		System.out.println("jestuser"+user.toString());
		Optional<ChatUser> existingChatUserOpt = chatUserService.findByNickName(nickName);
		
		//userFromDatabase.getEmailAdress().equals(user.getEmailAdress()
		//UniqueUser userFromDatabase = defaultUniqueUserDetailsService.findByUniqueUserEmile(user.getEmailAdress());
		System.out.println("witam");
		Optional<ChatUser> chatUserOpt = chatUserService.findByNickName(nickName);
		if (existingChatUserOpt.isPresent()) {
        model.addAttribute("errorMessage", "Email already exists.");
        return "register";
		}

		UniqueUser uniqueUser = new UniqueUser(encoder.encode(user.getPassword()),user.getName(), user.getEmailAdress(),new Role("User"));
		
		uniqueUserService.save(uniqueUser);
		System.out.println("user has been saved");
		ChatUser chatUser = new ChatUser(nickName, uniqueUser);
		chatUserService.save(chatUser);
		System.out.println("chat user has been saved");

		return "indexChat";
		
	
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
