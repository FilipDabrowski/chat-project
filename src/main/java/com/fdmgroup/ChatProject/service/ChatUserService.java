package com.fdmgroup.ChatProject.service;



import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.fdmgroup.ChatProject.model.Chat;

import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.repository.ChatUserRepository;
import com.fdmgroup.ChatProject.service.interfaces.IChatUserService;

@Service
public class ChatUserService implements IChatUserService{

	@Autowired
	private ChatUserRepository chatUserRepository; 

	
	@Override
	public Optional<ChatUser> findById(long userID) {
		// TODO Auto-generated method stub
		return chatUserRepository.findById(userID);
	}

	@Override
	public Optional<ChatUser> findByNickName(String nickName) {
		return chatUserRepository.findByNickName(nickName);
		
	}

	@Override
	public void addFrindToList(ChatUser chatUser, ChatUser friend) {
		
		Collection<ChatUser> friendList = chatUser.getFriendList();
		
		if(friendList.contains(friend)) {
			return;
		}
		friendList.add(friend);
		save(chatUser);
		
	}

	@Override
	public void addChatToUser(ChatUser currChatUser, Chat chat) {

		Collection<Chat> chatList = currChatUser.getChats();
		
		if(chatList.contains(chat)) {
			return;
		}
		chatList.add(chat);
		
		save(currChatUser);
	}

	@Override
	public void save(ChatUser chatUser) {
		chatUserRepository.save(chatUser);
	}

	@Override
	public Optional<ChatUser>  findByUser(UniqueUser name) {
		
	List<ChatUser> userList = chatUserRepository.findByUser(name);
	
	if(userList.isEmpty()) {
		return  Optional.empty();
	}
	return Optional.of(userList.get(0));
	}

	@Override
	public void addUserToBannedList(ChatUser user, ChatUser currUser) {
		
		Collection<ChatUser> bannedList = currUser.getBlockedUsers();
		
		if(bannedList.contains(user)) {
			return;
		}
		bannedList.add(user);
		save(currUser);
		
	}

	@Override
	public void unblockUser(ChatUser userToUnblock, ChatUser currUser) {
		Collection<ChatUser> bannedList = currUser.getBlockedUsers();
		
		if(bannedList.contains(userToUnblock)) {
			bannedList.remove(userToUnblock);
		}
		save(currUser);
	}

	@Override
	public void removeFriend(ChatUser userToUnfriend, ChatUser currUser) {
		Collection<ChatUser> friendList = currUser.getFriendList();
		
		if(friendList.contains(userToUnfriend)) {
			friendList.remove(userToUnfriend);
		}
		save(currUser);
	}

}
