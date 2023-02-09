package com.fdmgroup.ChatProject.service.interfaces;


import java.util.Optional;
import com.fdmgroup.ChatProject.model.UniqueUser;

public interface IUniqueUserService {

	void save(UniqueUser userTwo);

	Optional<UniqueUser> findByName(String name);
	
}
