package com.fdmgroup.ChatProject.service.interfaces;


import java.util.Optional;

import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.UniqueUser;

public interface IChatUserService {

	Optional<ChatUser> findById(long userID);

	void save(ChatUser adminChat);

	Optional<ChatUser> findByUser(UniqueUser name);

	Optional<ChatUser> findByNickName(String nickName);

	void addFrindToList(ChatUser chatUser, ChatUser chatUser2);

	void addChatToUser(ChatUser currChatUser, Chat chat);

}
