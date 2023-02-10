package com.fdmgroup.ChatProject.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.ChatUser;

public interface IChatService {

	void addUserToChat(Chat chat, ChatUser user);

	Optional<Chat> findById(long chatID);

	void save(Chat chat);

	List<Chat> findAll();

}
