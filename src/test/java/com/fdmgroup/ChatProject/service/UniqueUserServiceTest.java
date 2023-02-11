package com.fdmgroup.ChatProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.ChatProject.model.BannedUser;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.repository.UniqueUserRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class UniqueUserServiceTest {

	@InjectMocks
	UniqueUserService uniqueUserServiceMock;
	
	@MockBean
	UniqueUserRepository uniqueUserRepositoryMock;
	
	@Test
	public void saveTest() {
		UniqueUser uniqueUser = new UniqueUser(); 

		uniqueUserServiceMock.save(uniqueUser);
		
		verify(uniqueUserRepositoryMock, times(1)).save(uniqueUser);
	}
	
	
	@Test
	public void findByNameTest() {
		String uniqueUserName = "name"; 
		UniqueUser uniqueUser = new UniqueUser();
		Optional<UniqueUser> uniqueUserOpt = Optional.of(uniqueUser);
		when(uniqueUserRepositoryMock.findByName(uniqueUserName)).thenReturn(uniqueUserOpt);
		
		Optional<UniqueUser> returnedUniqueUserOpt = uniqueUserServiceMock.findByName(uniqueUserName);
		
		verify(uniqueUserRepositoryMock, times(1)).findByName(uniqueUserName);
		assertEquals(uniqueUserOpt, returnedUniqueUserOpt);
	}
	
	
	
	@Test
	public void findByIdTest() {
		long uniqueUserID = 1L; 
		UniqueUser uniqueUser = new UniqueUser();
		Optional<UniqueUser> uniqueUserOpt = Optional.of(uniqueUser);
		when(uniqueUserRepositoryMock.findById(uniqueUserID)).thenReturn(uniqueUserOpt);
		
		Optional<UniqueUser> returneduniqueUserOpt = uniqueUserServiceMock.findById(uniqueUserID);
		
		verify(uniqueUserRepositoryMock, times(1)).findById(uniqueUserID);
		assertEquals(uniqueUserOpt, returneduniqueUserOpt);
	}
	
	
}
