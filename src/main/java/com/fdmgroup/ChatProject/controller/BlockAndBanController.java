package com.fdmgroup.ChatProject.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.ChatProject.model.BannedUser;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.service.BannedUserService;
import com.fdmgroup.ChatProject.service.interfaces.IBannedUserService;
import com.fdmgroup.ChatProject.service.interfaces.IChatService;
import com.fdmgroup.ChatProject.service.interfaces.IChatUserService;
import com.fdmgroup.ChatProject.service.interfaces.IRoleService;
import com.fdmgroup.ChatProject.service.interfaces.IUniqueUserService;

@Controller
public class BlockAndBanController {

	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUniqueUserService uniqueUserService;
	@Autowired
	private IChatUserService chatUserService;
	@Autowired
	private IBannedUserService bannedUserService;

	@PostMapping("/blockUser")
	public String blockUser(ModelMap model, Authentication authentication, @RequestParam("nickName") String nickName) {

		String name = authentication.getName();
		Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);
		Optional<ChatUser> chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());
		ChatUser currUser = chatUserOpt.get();

		Optional<ChatUser> userToBanOpt = chatUserService.findByNickName(nickName);

		userToBanOpt.ifPresent(user -> {
			if (!user.equals(currUser)) {
				chatUserService.addUserToBlockedList(user, currUser);
			}
		});

		model.addAttribute("currentUser", currUser);
		return getReturnPage(model, currUser);
	}

	@PostMapping("/banUser")
	public String banUser(ModelMap model, Authentication authentication, @RequestParam("nickName") String nickName) {

		String name = authentication.getName();
		Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);
		Optional<ChatUser> chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());
		ChatUser currUser = chatUserOpt.get();

		Optional<ChatUser> userToBanOpt = chatUserService.findByNickName(nickName);
		if (userToBanOpt.isPresent()) {
			ChatUser userToBan = userToBanOpt.get();

			if (!bannedUserService.findByBannedUser(userToBan).isPresent()) {
				BannedUser bannedUser = new BannedUser(userToBan);
				if (!bannedUser.getBannedUser().getUser().getRole().getRoleName().equals("Admin")) {
					bannedUser.getBannedUser().getUser().setLocked(true);
					bannedUserService.save(bannedUser);
				}
			}

		}

		model.addAttribute("currentUser", currUser);

		return getReturnPage(model, currUser);
	}

	
	@PostMapping("/unBan/{id}")
	public String unbanUser(ModelMap model, Authentication authentication,
			@PathVariable("id") long bannedUserID) {

		String name = authentication.getName();
		Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);
		Optional<ChatUser> chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());
		ChatUser currUser = chatUserOpt.get();
		
		Optional<BannedUser> toUnbanOpt = bannedUserService.findById(bannedUserID);
		
		if (toUnbanOpt.isPresent()) {
		toUnbanOpt.get().getBannedUser().getUser().setLocked(false);
		bannedUserService.removeBannedUser(bannedUserID);
		}
		
		model.addAttribute("currentUser", currUser);

		return getReturnPage(model, currUser);
	}
	
	@PostMapping("/unBlock/{id}")
	public String unblockUser(ModelMap model, Authentication authentication,
			@PathVariable("id") long blockedChatUserID) {

		String name = authentication.getName();
		Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);
		Optional<ChatUser> chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());
		ChatUser currUser = chatUserOpt.get();

		Optional<ChatUser> userToUnblockOpt = chatUserService.findById(blockedChatUserID);
		if (userToUnblockOpt.isPresent()) {
			ChatUser userToUnblock = userToUnblockOpt.get();
				chatUserService.unblockUser(userToUnblock,currUser);
		}

		model.addAttribute("currentUser", currUser);

		return getReturnPage(model, currUser);
	}
	
	
	@PostMapping("/deletFriend/{id}")
	public String deleteFriend(ModelMap model, Authentication authentication,
			@PathVariable("id") long removeFriendID) {

		String name = authentication.getName();
		Optional<UniqueUser> uniqueUserOpt = uniqueUserService.findByName(name);
		Optional<ChatUser> chatUserOpt = chatUserService.findByUser(uniqueUserOpt.get());
		ChatUser currUser = chatUserOpt.get();

		Optional<ChatUser> userToUnfriendOpt = chatUserService.findById(removeFriendID);
		if (userToUnfriendOpt.isPresent()) {
			ChatUser userToUnblock = userToUnfriendOpt.get();
				chatUserService.removeFriend(userToUnblock,currUser);
		}

		model.addAttribute("currentUser", currUser);

		return getReturnPage(model, currUser);
	}
	
	
	
	
	
	
	
	
	
	private String getReturnPage(ModelMap model, ChatUser user) {

		if (user.getUser().getRole().equals(roleService.findByRoleName("Admin"))) {
			model.addAttribute("bannedUsers", bannedUserService.findAll());
			return "/admin/allSetting";
		}
		return "profileSetting";
	}


	
}
