package com.fdmgroup.ChatProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fdmgroup.ChatProject.model.UniqueUser;


public interface UniqueUserRepository extends JpaRepository<UniqueUser, Long> {

	Optional<UniqueUser> findByEmail(String emailAdress);

	Optional<UniqueUser> findByName(String name);



}
