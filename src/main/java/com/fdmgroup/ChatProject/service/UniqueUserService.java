package com.fdmgroup.ChatProject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.repository.UniqueUserRepository;
import com.fdmgroup.ChatProject.service.interfaces.IUniqueUserService;

@Service
public class UniqueUserService implements IUniqueUserService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	private UniqueUserRepository uniqueUserRepository;
	
	public void save(UniqueUser uniqueUser) {
		uniqueUserRepository.save(uniqueUser);
		
	
		
	}

	@Override
	public Optional<UniqueUser> findByName(String name) {

		return uniqueUserRepository.findByName(name);
		
		
	}


	public Optional<UniqueUser> findById(Long id) {
		
		return uniqueUserRepository.findById(id);
	}
	public void changePassword(Long id, String password) {
		Optional<UniqueUser> uniqueUserOpt = uniqueUserRepository.findById(id);
		UniqueUser uniqueUser = uniqueUserOpt.get();
		uniqueUser.setPassword(passwordEncoder.encode(password));
	}

	

}
