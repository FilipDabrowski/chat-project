package com.fdmgroup.ChatProject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

@Controller
public class UserController {

	@Autowired
	private UniqueUserService uniqueUserService;
	@Autowired
	private ChatUserService chatUserService;
	@Autowired
	private DefaultUniqueUserDetailsService defaultUniqueUserDetailsService;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	//private PasswordEncoder passwordEncoder;

//	@GetMapping("/change-password")
//	public String changePasswordPage(@ModelAttribute("uniqueUser") UniqueUser uniqueUser) {
//			
//			return "auth/changePassword";
//		}

	// @PostMapping("/changePassword/{id}")
//	@GetMapping("/changePassword")
//		public String changePasswordPage(@ModelAttribute("uniqueeUser") UniqueUser uniqueUser) {
//		return "/changePassword/{id}";
//	}

	@PostMapping("/changePassword/{id}")
	public String changePassword(@PathVariable Long id, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("confirmNewPassword") String confirmNewPassword, Model model) {
		
		Optional<ChatUser> currentChatUserOptional = chatUserService.findById(id);
		UniqueUser currentUniqueUser = currentChatUserOptional.get().getUser();
		boolean isPasswordSame = passwordEncoder.matches(oldPassword, currentUniqueUser.getPassword());
		//String hashedNewPassword = passwordEncoder.encode(newPassword);
		
		//UserDetails userDetails = defaultUniqueUserDetailsService.loadUserByIdForPasswordChange(id);
		//UniqueUserPrincipal uniqueUserPrincipal = (UniqueUserPrincipal) userDetails;
		Long idUniqueUser = currentUniqueUser.getId();
		if (isPasswordSame == true) {
			System.out.println("haslo ok");
			
			//currentUniqueUser.setPassword(hashedNewPassword);
			//uniqueUserService.changePassword(idUniqueUser, newPassword);
			CharSequence newPass = confirmNewPassword;
			//String testPass = "12345";
			System.out.println("confirm password is:" + confirmNewPassword);
			currentUniqueUser.setPassword(passwordEncoder.encode(newPass));
			uniqueUserService.save(currentUniqueUser);
			System.out.println(currentUniqueUser.getPassword());
			//uniqueUserPrincipal.setPassword(hashedNewPassword);
			//userDetails = (UserDetails) uniqueUserPrincipal;
			//defaultUniqueUserDetailsService.saveUserToDb(uniqueUserPrincipal);

			return "redirect:/login";

		} else {
			System.out.println("password does not match");
			return "redirect:/login";
		}

	}

	@GetMapping("/settings")
	public String goToProfileSettings(ModelMap model, Authentication authentication) {

		String name = authentication.getName();
		System.out.println(name);
		Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);
		if (uniqueUserOpt.isPresent()) {
			Optional<ChatUser> chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());
			chatUserOpt.ifPresent((chatUser) -> model.addAttribute("currentUser", chatUser));

		}

		return "profileSetting";

	}
}
