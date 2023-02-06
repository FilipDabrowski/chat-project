package com.fdmgroup.ChatProject.model;

import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class UniqueUser {
	@Entity
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String password;
	private String name; 
	private String emailAdress;
	
		
	
	@Override
	public int hashCode() {
		return Objects.hash(emailAdress, id, name, password);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UniqueUser other = (UniqueUser) obj;
		return Objects.equals(emailAdress, other.emailAdress) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(password, other.password);
	}
	
	
	@Override
	public String toString() {
		return "UniqueUser [id=" + id + ", password=" + password + ", name=" + name + ", emailAdress=" + emailAdress
				+ "]";
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmailAdress() {
		return emailAdress;
	}
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}
	public Long getId() {
		return id;
	}
	

	
}
