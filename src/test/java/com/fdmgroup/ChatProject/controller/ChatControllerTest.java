package com.fdmgroup.ChatProject.controller;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.ChatProject.ChatProjectApplication;
import com.fdmgroup.ChatProject.model.BannedUser;
import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.Message;
import com.fdmgroup.ChatProject.model.Role;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.service.ChatService;
import com.fdmgroup.ChatProject.service.ChatUserService;
import com.fdmgroup.ChatProject.service.MessageService;
import com.fdmgroup.ChatProject.service.UniqueUserService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ChatProjectApplication.class})
public class ChatControllerTest {


	@MockBean
	private UniqueUserService uniqueUserServiceMock;
	@MockBean
	private ChatUserService chatUserServiceMock;
	@MockBean
	private ChatService chatServiceMock;
	@MockBean
	private MessageService messageServiceMock;	
	
	@Autowired
	private MockMvc mockMVC;
	
	//Mocks
	@Mock
	Role roleMock;
	@Mock
	Role processRoleMock;
	
	@Mock
	UniqueUser currUniqueUserMock;
	@Mock
	UniqueUser procrssUniqueUserMock;

	@Mock
	ChatUser currChatUserMock;
	@Mock
	ChatUser userForProcessMock;
		
	String chatUserID = "10000";
	
	
	
	@BeforeEach
	public void arrange() {

		//currUniqueUserMock.setRole(roleMock);
		when(currUniqueUserMock.getRole()).thenReturn(roleMock);
		//currChatUserMock.setUser(currUniqueUserMock);
		when(currChatUserMock.getUser()).thenReturn(currUniqueUserMock);
		
		//procrssUniqueUserMock.setRole(processRoleMock);
		when(procrssUniqueUserMock.getRole()).thenReturn(processRoleMock);		
		//userForProcessMock.setUser(procrssUniqueUserMock);
		when(userForProcessMock.getUser()).thenReturn(procrssUniqueUserMock);
		
		when(chatUserServiceMock.findById(Long.parseLong(chatUserID))).thenReturn(Optional.of(currChatUserMock));
	}
	

	@Test
	@WithMockUser(username = "mockUser")
	public void displayChatTest() throws Exception {
		
		when(chatUserServiceMock.findById(1)).thenReturn(Optional.of(userForProcessMock));
				
		Chat chat = new Chat("name", userForProcessMock, currChatUserMock);
		List<Chat> chatList = List.of( new Chat(),chat);
		when(chatServiceMock.findAll()).thenReturn(chatList);
		
		List<Message> messageList = List.of();
		when(messageServiceMock.findByChat(chat)).thenReturn(messageList);

		
		mockMVC.perform(post("/goToChatWith/1")
				.param("currentUserID", chatUserID))
				.andExpectAll(
						model().attribute("selectedChat", chat),
						model().attribute("chatMessages", messageList),
						model().attribute("currentUser", currChatUserMock))
				.andExpect(view().name("indexChat"));
	}
	
	@Test
	@WithMockUser(username = "mockUser")
	public void displayGroupChatTest() throws Exception {
		
		Chat chat = new Chat();	
		when(chatServiceMock.findById(1)).thenReturn(Optional.of(chat));
		List<Message> messageList = List.of();
		when(messageServiceMock.findByChat(chat)).thenReturn(messageList);

		
		mockMVC.perform(post("/goToGroupChat/1")
				.param("currentUserID", chatUserID))
				.andExpectAll(
						model().attribute("selectedChat", chat),
						model().attribute("chatMessages", messageList),
						model().attribute("currentUser", currChatUserMock))
				.andExpect(view().name("indexChat"));
	}
	
	
	@Test
	@WithMockUser(username = "mockUser")
	public void createNewGroupChatTest() throws Exception {
		
		
		when(uniqueUserServiceMock.findByName("mockUser")).thenReturn(Optional.of(currUniqueUserMock));
		when(chatUserServiceMock.findByUser(currUniqueUserMock)).thenReturn(Optional.of(currChatUserMock));
		
		
		mockMVC.perform(post("/createNewGroupChat"))
				.andExpectAll(
						model().attribute("currentUser", currChatUserMock))
				.andExpect(view().name("createChat"));
	}
	
	
	@Test
	@WithMockUser(roles = "Admin",username = "mockUser")
	public void createChatTest() throws Exception {
		
		when(uniqueUserServiceMock.findByName("mockUser")).thenReturn(Optional.of(currUniqueUserMock));
		when(chatUserServiceMock.findByUser(currUniqueUserMock)).thenReturn(Optional.of(currChatUserMock));
		
		when(chatUserServiceMock.findByNickName("one")).thenReturn(Optional.of(userForProcessMock));
		
		String[] stringArray = {"one","one","one","one"};
		
		
		mockMVC.perform(post("/createChat")
				.param("chatName", "Chat")
				.param("userToAdd",stringArray))
				.andExpectAll(
						model().attribute("currentUser", currChatUserMock))
				.andExpect(view().name("indexChat"));
	
		
		verify(chatServiceMock,times(5)).addUserToChat(any(),any());
		verify(chatServiceMock,times(1)).save(any());
		verify(chatUserServiceMock,times(5)).addChatToUser(any(), any());
		verify(chatUserServiceMock,atLeastOnce()).save(any());
	}
	
	
	
	
	
}
