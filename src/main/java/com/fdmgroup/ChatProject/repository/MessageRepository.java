package com.fdmgroup.ChatProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

	List<Message> findByChat(Chat chat);

}
