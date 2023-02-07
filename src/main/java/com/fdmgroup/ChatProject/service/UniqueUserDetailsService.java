//package com.fdmgroup.ChatProject.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.fdmgroup.ChatProject.model.UniqueUser;
//import com.fdmgroup.ChatProject.repository.UniqueUserRepository;
//import com.fdmgroup.ChatProject.security.UniqueUserDetails;
//
//
//
//@Service
//public class UniqueUserDetailsService implements UserDetailsService {
//
//	@Autowired
//	private  UniqueUserRepository uniqueUserRepository;
//	
//	private  Authentication authentication;
//	
//	public UniqueUserDetailsService() {};
//	
//	public UniqueUserDetailsService(UniqueUserRepository uniqueUserRepository,Authentication authentication) {
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
//		Optional<UniqueUser> uniqueUser = uniqueUserRepository.findByEmail(email);
//		System.out.println("UniqueUserDetailsService class is working/ loadUserByUsername ");
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
//		Optional<UniqueUser> uniqueUser = uniqueUserRepository.findByEmail(email);
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
//		Optional<UniqueUser> uniqueUser = uniqueUserRepository.findByEmail(email);
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
//}
