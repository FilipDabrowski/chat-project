package com.fdmgroup.ChatProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.ChatProject.model.ChatUser;

public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

}
