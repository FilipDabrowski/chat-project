package com.fdmgroup.ChatProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
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

import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.repository.ChatRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ChatServiceTest {

	@InjectMocks
	ChatService chatServiceMock;
	
	@MockBean
	ChatRepository chatRepositoryMock;
	
	@Test
	public void saveTestSuccedSave() {
		Chat chat = new Chat(); 
		List<Chat> chatList = List.of();
		
		when(chatServiceMock.findAll()).thenReturn(chatList);
		
		chatServiceMock.save(chat);
		
		verify(chatRepositoryMock, times(1)).save(chat);
	}
	@Test
	public void saveTestFailSave() {
		Chat chat = new Chat(); 
		List<Chat> chatList = List.of(chat);
		
		when(chatServiceMock.findAll()).thenReturn(chatList);
		
		chatServiceMock.save(chat);
		
		verify(chatRepositoryMock, times(0)).save(chat);
	}
	
	@Test
	public void findAllTest() {
		List<Chat> someList = new ArrayList<>();
		when(chatRepositoryMock.findAll()).thenReturn(someList);
		
		List<Chat> returnedList = chatServiceMock.findAll();
		
		verify(chatRepositoryMock, times(1)).findAll();
		assertEquals(someList, returnedList);
	}
	
	
	@Test
	public void findByIdTest() {
		long chatID = 1L; 
		Chat chat = new Chat(); 
		Optional<Chat> chatOpt = Optional.of(chat);
		when(chatRepositoryMock.findById(chatID)).thenReturn(chatOpt);
		
		Optional<Chat> returnedBannedUserOpt = chatServiceMock.findById(chatID);
		
		verify(chatRepositoryMock, times(1)).findById(chatID);
		assertEquals(chatOpt, returnedBannedUserOpt);
	}
	
	
	@Test
	public void addUserToChatTest() {
		Chat chatMock = mock(Chat.class);
		List<ChatUser> chatUserListMock = mock(List.class);
		ChatUser chatUser = new ChatUser();
		when(chatMock.getChattingUsers()).thenReturn(chatUserListMock);
		
		
		chatServiceMock.addUserToChat(chatMock, chatUser);
		
		
		verify(chatRepositoryMock, times(1)).save(chatMock);
		verify(chatMock,times(1)).getChattingUsers();
		verify(chatUserListMock,times(1)).add(chatUser);
	}
	
	
}
