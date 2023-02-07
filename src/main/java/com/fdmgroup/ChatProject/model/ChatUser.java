package com.fdmgroup.ChatProject.model;

import java.util.Collection;
import java.util.Objects;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class ChatUser {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String nickName;
	
	@OneToOne
	private UniqueUser user;
	
	@ManyToMany
	private Collection<ChatUser> friendList;

	@ManyToMany
	private Collection<ChatUser> blockedUsers;

	@ManyToMany
	private Collection<Chat> chats;
	

	public ChatUser() {
		friendList = new TreeSet<>();
		blockedUsers = new TreeSet<>();
		chats = new TreeSet<>();
	}
	
	public ChatUser(String nickName, UniqueUser user) {
		this();
		this.nickName = nickName;
		this.user = user;
	}
	
	public ChatUser(String nickName, UniqueUser user, Collection<ChatUser> friendList,
			Collection<ChatUser> blockedUsers, Collection<Chat> chats) {
		this.nickName = nickName;
		this.user = user;
		this.friendList = friendList;
		this.blockedUsers = blockedUsers;
		this.chats = chats;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public UniqueUser getUser() {
		return user;
	}

	public void setUser(UniqueUser user) {
		this.user = user;
	}

	public Collection<ChatUser> getFriendList() {
		return friendList;
	}

	public void setFriendList(Collection<ChatUser> friendList) {
		this.friendList = friendList;
	}

	public Collection<ChatUser> getBlockedUsers() {
		return blockedUsers;
	}

	public void setBlockedUsers(Collection<ChatUser> blockedUsers) {
		this.blockedUsers = blockedUsers;
	}

	public Collection<Chat> getChats() {
		return chats;
	}

	public void setChats(Collection<Chat> chats) {
		this.chats = chats;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nickName, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatUser other = (ChatUser) obj;
		return Objects.equals(nickName, other.nickName) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "ChatUser [nickName=" + nickName + ", user=" + user + "]";
	}
	
	
	

	
	
}
