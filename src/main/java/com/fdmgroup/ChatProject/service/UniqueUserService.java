package com.fdmgroup.ChatProject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.repository.UniqueUserRepository;
import com.fdmgroup.ChatProject.service.interfaces.IUniqueUserService;

@Service
public class UniqueUserService implements IUniqueUserService {

	@Autowired
	private UniqueUserRepository uniqueUserRepository;
	
	public void save(UniqueUser uniqueUser) {
		uniqueUserRepository.save(uniqueUser);
		
	
		
	}

	public Optional <UniqueUser> findById(Long id) {
		
		return uniqueUserRepository.findById(id);
	}

	

}
