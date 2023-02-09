package com.fdmgroup.ChatProject.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fdmgroup.ChatProject.model.UniqueUser;
public interface IUniqueUserService {

	void save(UniqueUser userTwo);

	Optional<UniqueUser> findByName(String name);
	
}
