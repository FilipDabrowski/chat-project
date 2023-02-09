package com.fdmgroup.ChatProject.dataImport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.Role;
import com.fdmgroup.ChatProject.model.UniqueUser;

import com.fdmgroup.ChatProject.repository.RoleRepository;
import com.fdmgroup.ChatProject.service.UniqueUserService;
import com.fdmgroup.ChatProject.service.interfaces.IChatUserService;


@Component
public class DataImport implements ApplicationRunner {

	@Autowired
	private UniqueUserService uniqueUserService;
	
	@Autowired
	private IChatUserService chatUserService;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		Role roleAdmin = new Role("Admin");
		Role roleUser = new Role("User");	
		roleRepository.save(roleAdmin);
		roleRepository.save(roleUser);
		
		UniqueUser admin = new UniqueUser(encoder.encode("admin"),"admin","admin@admin.com",roleAdmin);
		uniqueUserService.save(admin);	
		ChatUser adminChat = new ChatUser("nick admin", admin);
		chatUserService.save(adminChat);	

		UniqueUser user = new UniqueUser(encoder.encode("user"),"user","user@user.com",roleUser);
		uniqueUserService.save(user);		
		ChatUser userChat = new ChatUser("nick user", user);
		chatUserService.save(userChat);
		
		UniqueUser user1 = new UniqueUser(encoder.encode("user"),"user1","user1@user.com",roleUser);
		uniqueUserService.save(user1);
		ChatUser user1Chat = new ChatUser("nick 1 user", user1);
		chatUserService.save(user1Chat);
		
		UniqueUser user2 = new UniqueUser(encoder.encode("user"),"user2","user2@user.com",roleUser);
		uniqueUserService.save(user2);	
		ChatUser user2Chat = new ChatUser("nick 2 user", user2);
		chatUserService.save(user2Chat);		
		
		UniqueUser user3 = new UniqueUser(encoder.encode("user"),"user3","user3@user.com",roleUser);
		uniqueUserService.save(user3);	
		ChatUser user3Chat = new ChatUser("nick 3 user", user3);
		chatUserService.save(user3Chat);	
		
		UniqueUser user4 = new UniqueUser(encoder.encode("user"),"user4","user4@user.com",roleUser);
		uniqueUserService.save(user4);	
		ChatUser user4Chat = new ChatUser("nick 4 user", user4);
		chatUserService.save(user4Chat);	
		
		UniqueUser user5 = new UniqueUser(encoder.encode("user"),"user5","user5@user.com",roleUser);
		uniqueUserService.save(user5);		
		ChatUser user5Chat = new ChatUser("nick 5 user", user5);
		chatUserService.save(user5Chat);	
		
		UniqueUser user6 = new UniqueUser(encoder.encode("user"),"user6","user6@user.com",roleUser);
		uniqueUserService.save(user6);		
		ChatUser user6Chat = new ChatUser("nick 6 user", user6);
		chatUserService.save(user6Chat);	
		
		UniqueUser user7 = new UniqueUser(encoder.encode("user"),"user7","user7@user.com",roleUser);
		uniqueUserService.save(user7);		
		ChatUser user7Chat = new ChatUser("nick 7 user", user7);
		chatUserService.save(user7Chat);	
		
		UniqueUser user8 = new UniqueUser(encoder.encode("user"),"user8","user8@user.com",roleUser);
		uniqueUserService.save(user8);		
		ChatUser user8Chat = new ChatUser("nick 8 user", user8);
		chatUserService.save(user8Chat);	
	
		UniqueUser user9 = new UniqueUser(encoder.encode("user"),"user9","user9@user.com",roleUser);
		uniqueUserService.save(user9);	
		ChatUser user9Chat = new ChatUser("nick 9 user", user9);
		chatUserService.save(user9Chat);
		
	
		

		
		
		System.out.println("End of Data Import");
	}

}
