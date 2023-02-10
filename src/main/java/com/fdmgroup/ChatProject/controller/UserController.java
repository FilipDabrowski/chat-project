package com.fdmgroup.ChatProject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.security.DefaultUniqueUserDetailsService;
import com.fdmgroup.ChatProject.service.ChatUserService;
import com.fdmgroup.ChatProject.service.UniqueUserService;


@Controller
public class UserController {
	
	@Autowired
	private UniqueUserService uniqueUserService;
	@Autowired
	private ChatUserService chatUserService;
	@Autowired
	private DefaultUniqueUserDetailsService defaultUniqueUserDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@GetMapping("/change-password")
//	public String changePasswordPage(@ModelAttribute("uniqueUser") UniqueUser uniqueUser) {
//			
//			return "auth/changePassword";
//		}
	
		
	 //@PostMapping("/changePassword/{id}")
	@PostMapping("/changePassword/{id}")
	    public String changePassword(@PathVariable Long id,
	                                 @RequestParam("oldPassword") String oldPassword,
	                                 @RequestParam("newPassword") String newPassword,
	                                 @RequestParam("confirmNewPassword") String confirmNewPassword,
	                                 Model model) {

	        Optional<UniqueUser> currentUser = uniqueUserService.findById(id);
	        UniqueUser currentUser1;
	        if(currentUser.isPresent()) {
	        	currentUser1 = currentUser.get();
	        
	        

	        if (!passwordEncoder.matches(oldPassword, currentUser1.getPassword())) {
	            model.addAttribute("error", "Incorrect old password");
	            return "changePassword";
	        }

	        if (!newPassword.equals(confirmNewPassword)) {
	            model.addAttribute("error", "New passwords do not match");
	            return "changePassword";
	        }

	        currentUser1.setPassword(passwordEncoder.encode(newPassword));
	        uniqueUserService.save(currentUser1);
	        model.addAttribute("success", "Password changed successfully");
	        return "changePassword";
	    }
			return "redirect:/indexChat";
	  
			
//		@PostGet("/editingProfil/{id}")
//		public String goToProfileSettings()
}
}
