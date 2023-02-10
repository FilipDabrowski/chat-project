package com.fdmgroup.ChatProject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.security.DefaultUniqueUserDetailsService;
import com.fdmgroup.ChatProject.security.UniqueUserPrincipal;
import com.fdmgroup.ChatProject.service.ChatUserService;
import com.fdmgroup.ChatProject.service.UniqueUserService;
import com.fdmgroup.ChatProject.service.interfaces.IBannedUserService;
import com.fdmgroup.ChatProject.service.interfaces.IRoleService;



@Controller
public class UserController {

	@Autowired
	private UniqueUserService uniqueUserService;
	@Autowired
	private ChatUserService chatUserService;
	@Autowired
	private DefaultUniqueUserDetailsService defaultUniqueUserDetailsService;
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IBannedUserService bannedUserService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	
//	@GetMapping("/change-password")
//	public String changePasswordPage(@ModelAttribute("uniqueUser") UniqueUser uniqueUser) {
//			
//			return "auth/changePassword";
//		}
	
		
	 //@PostMapping("/changePassword/{id}")
//	@GetMapping("/changePassword")
//		public String changePasswordPage(@ModelAttribute("uniqueeUser") UniqueUser uniqueUser) {
//		return "/changePassword/{id}";
//	}
	
	@PostMapping("/changePassword/{id}")
	    public String changePassword(@PathVariable Long id,
	                                 @RequestParam("oldPassword") String oldPassword,
	                                 @RequestParam("newPassword") String newPassword,
	                                 @RequestParam("confirmNewPassword") String confirmNewPassword,
	                                 Model model) {
		
		Optional<UniqueUser> currentUserOptional = uniqueUserService.findById(id);
		boolean isPasswordSame = passwordEncoder.matches
								(oldPassword, currentUserOptional
								.get().getPassword());
		
		//String hashedOldPassword = passwordEncoder.encode(oldPassword);
		String hashedNewPassword = passwordEncoder.encode(newPassword);
		
		UserDetails userDetails = defaultUniqueUserDetailsService.loadUserByIdForPasswordChange(id);
		UniqueUserPrincipal uniqueUserPrincipal = (UniqueUserPrincipal) userDetails;
		
		if(isPasswordSame) {
			uniqueUserPrincipal.setPassword(hashedNewPassword);
		    userDetails = (UserDetails) uniqueUserPrincipal;
		    defaultUniqueUserDetailsService.saveUserToDb(uniqueUserPrincipal);
		    return "redirect:/login";
		    
		}else {
			System.out.println("password does not match");
			return "redirect:/login";
		}
		
}
	
		@GetMapping("/settings")
		public String goToProfileSettings(ModelMap model,Authentication authentication) {
			
			
			String name = authentication.getName();
			Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);
			
			if(uniqueUserOpt.isPresent()) {
			Optional<ChatUser> chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());		
			chatUserOpt.ifPresent((chatUser)-> model.addAttribute("currentUser",chatUser));
			
			if(chatUserOpt.get().getUser().getRole().equals(roleService.findByRoleName("Admin"))) {
				model.addAttribute("bannedUsers",bannedUserService.findAll());
				return "/admin/allSetting";
			}
			
			}
			
			
		return "profileSetting";
		
}
}
