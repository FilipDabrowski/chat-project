package com.fdmgroup.ChatProject.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Entity
public class Notification {
		
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id; 
	@OneToOne
	private Message message;
	private boolean read;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Message getMessage() {
		return message;
	}
	public void setMessage(Message message) {
		this.message = message;
	}
	public boolean isRead() {
		return read;
	}
	public void setRead(boolean read) {
		this.read = read;
	}
	@Override
	public String toString() {
		return "Notification [id=" + id + ", message=" + message + ", read=" + read + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(id, message, read);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notification other = (Notification) obj;
		return Objects.equals(id, other.id) && Objects.equals(message, other.message) && read == other.read;
	}
	
	
	
	
	
}
