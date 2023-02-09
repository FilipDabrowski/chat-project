package com.fdmgroup.ChatProject.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.UniqueUser;

public interface IChatUserService {

	ChatUser findById(long userID);

	void save(ChatUser adminChat);

	Optional<ChatUser> findByUser(UniqueUser name);

}
