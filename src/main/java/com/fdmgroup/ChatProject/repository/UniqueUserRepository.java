package com.fdmgroup.ChatProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fdmgroup.ChatProject.model.UniqueUser;

@Repository
public interface UniqueUserRepository extends JpaRepository<UniqueUser, Long> {

	Optional<UniqueUser> findByEmailAdress(String emailAdress);

	Optional<UniqueUser> findByName(String name);



}
