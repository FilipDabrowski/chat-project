package com.fdmgroup.ChatProject.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.fdmgroup.ChatProject.model.BannedUser;
import com.fdmgroup.ChatProject.model.ChatUser;


public interface IBannedUserService {

	void save(BannedUser bannedUser);

	List<BannedUser> findAll();

	Optional<BannedUser> findByBannedUser(ChatUser userToBan);

	Optional<BannedUser> findById(long bannedUser);

	void removeBannedUser(long bannedUserID);

}
