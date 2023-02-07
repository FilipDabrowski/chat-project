package com.fdmgroup.ChatProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.ChatProject.model.ChatUser;
@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

}
