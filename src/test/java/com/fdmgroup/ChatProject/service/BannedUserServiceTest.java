package com.fdmgroup.ChatProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.ChatProject.model.BannedUser;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.repository.BannedUserRepository;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class BannedUserServiceTest {

	
	@InjectMocks
	BannedUserService bannedUserServiceMock;
	
	@MockBean
	BannedUserRepository bannedUserRepositoryMock;
	

	@Test
	public void saveTest() {
		//Arrange
		BannedUser bannedUser = new BannedUser(); 
		//Act
		bannedUserServiceMock.save(bannedUser);
		//Assert
		verify(bannedUserRepositoryMock, times(1)).save(bannedUser);
	}
	
	
	@Test
	public void findAllTest() {
		List<BannedUser> someList = new ArrayList<>();
		when(bannedUserRepositoryMock.findAll()).thenReturn(someList);
		List<BannedUser> returnedList = bannedUserServiceMock.findAll();
		verify(bannedUserRepositoryMock, times(1)).findAll();
		assertEquals(someList, returnedList);
	}

	@Test
	public void findByBannedUserTest() {
		ChatUser chatUser = new ChatUser(); 
		BannedUser bannedUser = new BannedUser();
		Optional<BannedUser> bannedUserOpt = Optional.of(bannedUser);
		when(bannedUserRepositoryMock.findByBannedUser(chatUser)).thenReturn(bannedUserOpt);
		
		Optional<BannedUser> returnedBannedUserOpt = bannedUserServiceMock.findByBannedUser(chatUser);
		
		verify(bannedUserRepositoryMock, times(1)).findByBannedUser(chatUser);
		assertEquals(bannedUserOpt, returnedBannedUserOpt);
	}

	@Test
	public void findByIdTest() {
		long bannedUserID = 1L; 
		BannedUser bannedUser = new BannedUser();
		Optional<BannedUser> bannedUserOpt = Optional.of(bannedUser);
		when(bannedUserRepositoryMock.findById(bannedUserID)).thenReturn(bannedUserOpt);
		
		Optional<BannedUser> returnedBannedUserOpt = bannedUserServiceMock.findById(bannedUserID);
		
		verify(bannedUserRepositoryMock, times(1)).findById(bannedUserID);
		assertEquals(bannedUserOpt, returnedBannedUserOpt);
	}

	@Test
	public void removeBannedUserTest() {
		long bannedUserID = 1L; 

		bannedUserServiceMock.removeBannedUser(bannedUserID);

		verify(bannedUserRepositoryMock, times(1)).deleteById(bannedUserID);
	}
	
	
	
	
}
