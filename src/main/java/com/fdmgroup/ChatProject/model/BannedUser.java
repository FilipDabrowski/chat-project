package com.fdmgroup.ChatProject.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class BannedUser {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@OneToOne
	private ChatUser bannedUser;


	public ChatUser getBannedUser() {
		return bannedUser;
	}


	public void setBannedUser(ChatUser bannedUser) {
		this.bannedUser = bannedUser;
	}


	public BannedUser(ChatUser bannedUser) {
		this.bannedUser = bannedUser;
	}

	public BannedUser() {
	}


	@Override
	public int hashCode() {
		return Objects.hash(bannedUser);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BannedUser other = (BannedUser) obj;
		return Objects.equals(bannedUser, other.bannedUser);
	}


	@Override
	public String toString() {
		return "BannedUser [bannedUser=" + bannedUser + "]";
	}
	
	
	



}
