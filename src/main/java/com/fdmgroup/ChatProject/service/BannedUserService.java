package com.fdmgroup.ChatProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.ChatProject.repository.BannedUserRepository;
import com.fdmgroup.ChatProject.service.interfaces.IBannedUserService;

@Service
public class BannedUserService implements IBannedUserService {

	@Autowired
	private BannedUserRepository bannedUserRepository;
	
	
	
}
