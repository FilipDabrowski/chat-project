package com.fdmgroup.ChatProject.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Collection<ChatUser> chattingUsers;

	private LocalDateTime starttimeOfChat = LocalDateTime.now();

	public Chat() {
		chattingUsers = new ArrayList<>();
	}

	public Chat(ChatUser user1, ChatUser user2, ChatUser... chatUsers) {
		chattingUsers = new ArrayList<>();
		chattingUsers.add(user1);
		chattingUsers.add(user2);
		for (ChatUser user : chatUsers) {
			chattingUsers.add(user);
		}
	}

	public Chat(Collection<ChatUser> chattingUsers) {
		this.chattingUsers = chattingUsers;
	}
	
	
	public Collection<ChatUser> getChattingUsers() {
		return chattingUsers;
	}

	public void setChattingUsers(Collection<ChatUser> chattingUsers) {
		this.chattingUsers = chattingUsers;
	}

	public LocalDateTime getStarttimeOfChat() {
		return starttimeOfChat;
	}

	public void setStarttimeOfChat(LocalDateTime starttimeOfChat) {
		this.starttimeOfChat = starttimeOfChat;
	}

}
