package com.fdmgroup.ChatProject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.ChatProject.model.BannedUser;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.repository.BannedUserRepository;
import com.fdmgroup.ChatProject.service.interfaces.IBannedUserService;

@Service
public class BannedUserService implements IBannedUserService {

	@Autowired
	private BannedUserRepository bannedUserRepository;

	@Override
	public void save(BannedUser bannedUser) {
		bannedUserRepository.save(bannedUser);
	}

	@Override
	public List<BannedUser> findAll() {

		return bannedUserRepository.findAll();
	}

	@Override
	public Optional<BannedUser> findByBannedUser(ChatUser userToBan) {
		return bannedUserRepository.findByBannedUser(userToBan);
	}

	@Override
	public Optional<BannedUser> findById(long bannedUserID) {
		return bannedUserRepository.findById(bannedUserID);
	}

	@Override
	public void removeBannedUser(long bannedUserID) {
		bannedUserRepository.deleteById(bannedUserID);	
	}
	
	
	
}
