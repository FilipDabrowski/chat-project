package com.fdmgroup.ChatProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.ChatProject.model.BannedUser;

@Repository
public interface BannedUserRepository extends JpaRepository<BannedUser, Long>{
	
}
