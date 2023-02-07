package com.fdmgroup.ChatProject.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity
public class Chat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String chatName;

	@ManyToMany
	private Collection<ChatUser> chattingUsers;

	private LocalDateTime starttimeOfChat;

	public Chat() {
		starttimeOfChat = LocalDateTime.now();
		chattingUsers = new ArrayList<>();
	}

	public Chat(ChatUser user1, ChatUser user2, ChatUser... chatUsers) {
		starttimeOfChat = LocalDateTime.now();
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

	public String getChatName() {
		return chatName;
	}

	public void setChatName(String chatName) {
		this.chatName = chatName;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(chattingUsers, starttimeOfChat);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chat other = (Chat) obj;
		return Objects.equals(chattingUsers, other.chattingUsers)
				&& Objects.equals(starttimeOfChat, other.starttimeOfChat);
	}

	@Override
	public String toString() {
		return "Chat [chattingUsers=" + chattingUsers + ", starttimeOfChat=" + starttimeOfChat + "]";
	}



	
	
}
