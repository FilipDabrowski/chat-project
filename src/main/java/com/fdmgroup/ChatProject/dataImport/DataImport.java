package com.fdmgroup.ChatProject.dataImport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.service.interfaces.IUniqueUserService;

@Component
public class DataImport implements ApplicationRunner {

	@Autowired
	IUniqueUserService uniqueUserService;
	
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		UniqueUser userOne = new UniqueUser();
		userOne.setEmailAdress("userOne@blabla.bla");
		userOne.setName("User One");
		userOne.setPassword("1");
		userOne.setRole("SIMPLE_USER");
		
		
		UniqueUser userTwo = new UniqueUser();
		userOne.setEmailAdress("userTwo@blabla.bla");
		userOne.setName("User Two");
		userOne.setPassword("2");
		userOne.setRole("SIMPLE_USER");
		
		UniqueUser userAdmin = new UniqueUser();
		userOne.setEmailAdress("userAdmin@blabla.bla");
		userOne.setName("User Admin");
		userOne.setPassword("admin");
		userOne.setRole("ADMIN");
		
		uniqueUserService.save(userOne);
		uniqueUserService.save(userTwo);
		uniqueUserService.save(userAdmin);
		
		
	}

}
