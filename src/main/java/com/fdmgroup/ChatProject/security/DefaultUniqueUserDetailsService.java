package com.fdmgroup.ChatProject.security;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.repository.UniqueUserRepository;







@Service
public class DefaultUniqueUserDetailsService implements UserDetailsService {

	
	private  UniqueUserRepository uniqueUserRepository;
	
	@Autowired
	public DefaultUniqueUserDetailsService(UniqueUserRepository uniqueUserRepository) {
		super();
		this.uniqueUserRepository = uniqueUserRepository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		UniqueUser uniqueUser = findByUniqueUserName(name);
		return new UniqueUserPrincipal(uniqueUser);
	}
	
	public UniqueUser findByUniqueUserName(String uniqueUserName) {
		Optional<UniqueUser>optionalUniqueUser = uniqueUserRepository.findByName(uniqueUserName);
		UniqueUser uniqueUser = optionalUniqueUser.orElse(new UniqueUser("default name")); // if user hasn't been found, then it returns new user with default name
	return uniqueUser;
	}
	
	public void saveUniqueUser(UniqueUser uniqueUser) {
	uniqueUserRepository.save(uniqueUser);
	}
	
	public void saveUserToDb(UniqueUserPrincipal uniqueUserPrincipal) {
		uniqueUserRepository.save(uniqueUserPrincipal.getUniqueUser());
	}
	
	public UniqueUser findByUniqueUserEmile(String emailAdress) {
		Optional<UniqueUser>optionalUniqueUser = uniqueUserRepository.findByEmailAdress(emailAdress);
		UniqueUser uniqueUser = optionalUniqueUser.orElse(new UniqueUser("default name")); // if user hasn't been found, then it returns new user with default name
		return uniqueUser;
		
	}
	
	public UserDetails loadUserByIdForPasswordChange(Long id) throws UsernameNotFoundException {
		Optional<UniqueUser> uniqueUser = uniqueUserRepository.findById(id);

		if (uniqueUser.isPresent()) {
			return new UniqueUserPrincipal(uniqueUser.get());
		}
		throw new UsernameNotFoundException("User not found");
	}

}

	


