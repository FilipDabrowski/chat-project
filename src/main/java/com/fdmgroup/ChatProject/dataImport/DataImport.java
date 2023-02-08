package com.fdmgroup.ChatProject.dataImport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fdmgroup.ChatProject.model.Role;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.service.UniqueUserService;


@Component
public class DataImport implements ApplicationRunner {

	@Autowired
	private UniqueUserService uniqueUserService;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role roleAdmin = new Role("Admin");
		Role roleUser = new Role("User");
		UniqueUser admin = new UniqueUser(encoder.encode("admin"),"admin","admin@admin.com",roleAdmin);
		UniqueUser user = new UniqueUser(encoder.encode("user"),"user","user@user.com",roleUser);
		
		
//		UniqueUser userTwo = new UniqueUser();
//		userOne.setEmailAdress("userTwo@blabla.bla");
//		userOne.setName("UserTwo");
//		userOne.setPassword("2");
//
//		
//		UniqueUser userAdmin = new UniqueUser();
//		userOne.setEmailAdress("userAdmin@blabla.bla");
//		userOne.setName("UserAdmin");
//		userOne.setPassword("admin");
//	
		
		uniqueUserService.save(admin);
		uniqueUserService.save(user);
		
		
		
	}

}
