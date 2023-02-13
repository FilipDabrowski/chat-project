package com.fdmgroup.ChatProject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

	// private PasswordEncoder passwordEncoder;

	@Autowired
	private IRoleService roleService;

	@Autowired
	private IBannedUserService bannedUserService;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/editProfile/{id}")
	public String editProfile(@PathVariable Long id, @RequestParam("nickName") String nickName,
			@RequestParam("name") String name, @RequestParam("emailAdress") String emailAdress, ModelMap model,
			Authentication authentication) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		Optional<ChatUser> currentChatUserOptional = chatUserService.findById(id);
		ChatUser currentChatUser = currentChatUserOptional.get();
		UniqueUser currentUniqueUser = currentChatUserOptional.get().getUser();
		model.addAttribute("currentUser", currentChatUser);
		currentUniqueUser.setName(name);
		currentUniqueUser.setEmailAdress(emailAdress);
		currentUniqueUser.setRole(currentUniqueUser.getRole());
		System.out.println(currentUniqueUser.getRole().getRoleName() + "nazwa roli");
		currentChatUser.setNickName(nickName);

		uniqueUserService.save(currentUniqueUser);
		chatUserService.save(currentChatUser);
		roleService.findByRoleName("Admin");
		if (currentChatUser.getUser().getRole().equals(roleService.findByRoleName("Admin"))) {
			System.out.println("ADMINADMIN");
			model.addAttribute("bannedUsers", bannedUserService.findAll());
			return "/admin/allSetting";
		}
		return "profileSetting";

	}

	@PostMapping("/changePassword/{id}")
	public String changePassword(@PathVariable Long id, @RequestParam("oldPassword") String oldPassword,
			@RequestParam("newPassword") String newPassword,
			@RequestParam("confirmNewPassword") String confirmNewPassword, ModelMap model) {

		Optional<ChatUser> currentChatUserOptional = chatUserService.findById(id);
		UniqueUser currentUniqueUser = currentChatUserOptional.get().getUser();
		boolean isPasswordSame = passwordEncoder.matches(oldPassword, currentUniqueUser.getPassword());

		if (isPasswordSame == true) {
			System.out.println("haslo ok");

			CharSequence newPass = confirmNewPassword;

			System.out.println("confirm password is:" + confirmNewPassword);
			currentUniqueUser.setPassword(passwordEncoder.encode(newPass));
			uniqueUserService.save(currentUniqueUser);
			System.out.println(currentUniqueUser.getPassword());

			return "login";

		} else {
			System.out.println("password does not match");
			return "login";
		}

	}

	@GetMapping("/settings")
	public String goToProfileSttings(ModelMap model, Authentication authentication) {

		String name = authentication.getName();
		System.out.println(name);
		Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);

		if (uniqueUserOpt.isPresent()) {
			System.out.println("User is present");
			Optional<ChatUser> chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());
			chatUserOpt.ifPresent((chatUser) -> model.addAttribute("currentUser", chatUser));

			roleService.findByRoleName("Admin");
			if (chatUserOpt.get().getUser().getRole().equals(roleService.findByRoleName("Admin"))) {
				System.out.println("ADMINADMIN");
				model.addAttribute("bannedUsers", bannedUserService.findAll());
				return "/admin/allSetting";
			}
			
		}

		return "profileSetting";

	}
}
