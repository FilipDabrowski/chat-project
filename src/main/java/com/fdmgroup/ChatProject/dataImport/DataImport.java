package com.fdmgroup.ChatProject.dataImport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.security.UniqueUserDetails;
import com.fdmgroup.ChatProject.service.UniqueUserDetailsService;

@Component
public class DataImport implements ApplicationRunner {

	@Autowired
	UniqueUserDetailsService uniqueUserDetailsService;
	
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		UniqueUser userOne = new UniqueUser();
		userOne.setEmailAdress("userOne@blabla.bla");
		userOne.setName("UserOne");
		userOne.setPassword("1");
		userOne.setRole("ROLE_SIMPLE_USER");
		
		
		UniqueUser userTwo = new UniqueUser();
		userOne.setEmailAdress("userTwo@blabla.bla");
		userOne.setName("UserTwo");
		userOne.setPassword("2");
		userOne.setRole("ROLE_SIMPLE_USER");
		
		UniqueUser userAdmin = new UniqueUser();
		userOne.setEmailAdress("userAdmin@blabla.bla");
		userOne.setName("UserAdmin");
		userOne.setPassword("admin");
		userOne.setRole("ROLE_ADMIN");
		
		
		UniqueUserDetails userOneDetail = new UniqueUserDetails(userOne);
		UniqueUserDetails userTwoDetail = new UniqueUserDetails(userTwo);
		UniqueUserDetails adminDetail = new UniqueUserDetails(userAdmin);
		
		uniqueUserDetailsService.saveUserToDb(userOneDetail);
		uniqueUserDetailsService.saveUserToDb(userTwoDetail);
		uniqueUserDetailsService.saveUserToDb(adminDetail);
		
		
		System.out.println("End of Data Import");
	}

}
