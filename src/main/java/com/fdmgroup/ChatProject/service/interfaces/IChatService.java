package com.fdmgroup.ChatProject.service.interfaces;

import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.ChatUser;

public interface IChatService {

	void addUserToChat(Chat chat, ChatUser user);

	Chat findById(long chatID);

	void save(Chat chat);

}
