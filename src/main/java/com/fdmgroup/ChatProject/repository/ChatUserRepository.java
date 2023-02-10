package com.fdmgroup.ChatProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.UniqueUser;
@Repository
public interface ChatUserRepository extends JpaRepository<ChatUser, Long> {

	List<ChatUser> findByUser(UniqueUser name);

}
