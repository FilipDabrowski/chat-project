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
	
	public UniqueUser findByUniqueUserEmileAdress(String emileAdress) {
		Optional<UniqueUser>optionalUniqueUser = uniqueUserRepository.findByEmailAdress(emileAdress);
		UniqueUser uniqueUser = optionalUniqueUser.orElse(new UniqueUser("default emile")); // if user hasn't been found, then it returns new user with default name
		return uniqueUser;
		
	}
//	
//	private  Authentication authentication;
//	
//	public DefaultUniqueUserDetailsService() {};
//	
//	public DefaultUniqueUserDetailsService(UniqueUserRepository uniqueUserRepository,Authentication authentication) {
//		this.uniqueUserRepository = uniqueUserRepository;
//		this.authentication=authentication;
//	}
//
//	
//	
//	public List<UniqueUser> getAllUsers(){
//		return uniqueUserRepository.findAll();
//	}
//	
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Optional<UniqueUser> uniqueUser = uniqueUserRepository.findByEmailAdress(email);
//		System.out.println("DefaultUniqueUserDetailsService class is working/ loadUserByUsername ");
//		
//		if (uniqueUser.isEmpty()) {
//			throw new UsernameNotFoundException("User not found");
//		}
//		
//		return new UniqueUserDetails(uniqueUser.get());
//	}
//	
//	
//	public UserDetails loadUserByEmailForPasswordChange(String email) throws UsernameNotFoundException {
//		Optional<UniqueUser> uniqueUser = uniqueUserRepository.findByEmailAdress(email);
//
//		if (uniqueUser.isPresent()) {
//			return new UniqueUserDetails(uniqueUser.get());
//		}
//		throw new UsernameNotFoundException("User not found");
//	}
//
//	public void saveUserToDb(UniqueUserDetails uniqueUserDetails) {
//		uniqueUserRepository.save(uniqueUserDetails.getUniqueUser());
//	}
//	
//	
//	 public void setAuthenticationObject (Authentication authentication2) {
//    	 this.authentication = authentication2;
//    }
//
//   public Authentication getAuthenticationObject() {
//	   return authentication;
//   }
//	
//	public UniqueUser findUserFromCurrentSession() {
//		String email = getAuthenticationObject().getName();
//		Optional<UniqueUser> uniqueUser = uniqueUserRepository.findByEmailAdress(email);
//		
//		if (uniqueUser.isPresent()) {
//			UserDetails userDetails =  new UniqueUserDetails(uniqueUser.get());
//			UniqueUserDetails uniqueUserDetails1 = (UniqueUserDetails) userDetails;
//			return uniqueUserDetails1.getUniqueUser();
//		}
//		
//		throw new UsernameNotFoundException("User not found");
//		
//	}
//	
}

	


