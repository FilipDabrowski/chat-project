package com.fdmgroup.ChatProject.service.interfaces;

import java.util.List;

import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.Message;

public interface IMessageService {

	void save(Message sendMessage);

	List<Message> findByChat(Chat chat);

}
