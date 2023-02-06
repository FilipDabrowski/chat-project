package com.fdmgroup.ChatProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.ChatProject.model.Chat;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Long> {

}
