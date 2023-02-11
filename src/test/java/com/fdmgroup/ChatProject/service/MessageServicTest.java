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
import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.Message;
import com.fdmgroup.ChatProject.repository.MessageRepository;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class MessageServicTest {

	@InjectMocks
	MessageService messageServiceMock;
	
	@MockBean
	MessageRepository messageRepositoryMock;
	
	
	@Test
	public void saveTest() {
		Message message = new Message(); 

		messageServiceMock.save(message);

		verify(messageRepositoryMock, times(1)).save(message);
	}
	
	
	
	
	@Test
	public void findByChatTest() {
		Chat chat = new Chat();
		List<Message> someList = new ArrayList<>();
		when(messageRepositoryMock.findByChat(chat)).thenReturn(someList);
		
		List<Message> returnedList = messageServiceMock.findByChat(chat);
		
		verify(messageRepositoryMock, times(1)).findByChat(chat);
		assertEquals(someList, returnedList);
	}
}
