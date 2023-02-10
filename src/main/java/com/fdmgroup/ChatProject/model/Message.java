package com.fdmgroup.ChatProject.model;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name= "message_id")
	private Long Id;
	
	private String message;
	
	private LocalDateTime timestamp;
	
	private String timeString;
	
	public String getTimeString() {
		return timeString;
	}

	public void setTimeString(String timeString) {
		this.timeString = timeString;
	}

	@ManyToOne
	private ChatUser sender;
	
	@ManyToOne
	private Chat chat;

	public Message() {
		this.timestamp = LocalDateTime.now();
		this.timeString = timestamp.format(DateTimeFormatter.ofPattern("yyyy.MM.dd  hh.mm"));
	}

	public Message(String message, ChatUser sender, Chat chat) {
		this.message = message;
		this.timestamp = LocalDateTime.now();
		this.timeString = timestamp.format(DateTimeFormatter.ofPattern("yyyy.MM.dd  hh.mm"));
		this.sender = sender;
		this.chat = chat;	
	}

	public Message(String message, LocalDateTime timestamp, ChatUser sender, Chat chat) {
		this.message = message;
		this.timestamp = timestamp;
		this.timeString = timestamp.format(DateTimeFormatter.ofPattern("yyyy.MM.dd  hh.mm"));
		this.sender = sender;
		this.chat = chat;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public ChatUser getSender() {
		return sender;
	}

	public void setSender(ChatUser sender) {
		this.sender = sender;
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	@Override
	public int hashCode() {
		return Objects.hash(chat, message, sender, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(chat, other.chat) && Objects.equals(message, other.message)
				&& Objects.equals(sender, other.sender) && Objects.equals(timestamp, other.timestamp);
	}

	@Override
	public String toString() {
		return "Message [message=" + message + ", timestamp=" + timestamp + ", sender=" + sender + ", chat=" + chat
				+ "]";
	}
	
}
