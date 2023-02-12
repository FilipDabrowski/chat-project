package com.fdmgroup.ChatProject.controller;

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
import com.fdmgroup.ChatProject.service.BannedUserService;
import com.fdmgroup.ChatProject.service.ChatService;
import com.fdmgroup.ChatProject.service.ChatUserService;
import com.fdmgroup.ChatProject.service.MessageService;
import com.fdmgroup.ChatProject.service.RoleService;
import com.fdmgroup.ChatProject.service.UniqueUserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ChatProjectApplication.class})
public class ChatUserProfileControllerTest {

	@MockBean
	private ChatService chatServiceMock;
	@MockBean
	private UniqueUserService uniqueUserServiceMock;
	@MockBean
	private ChatUserService chatUserServiceMock;
	@MockBean
	private MessageService messageServiceMock;
		
	@Autowired
	private MockMvc mockMVC;
	
	@Mock
	UniqueUser currUniqueUserMock;
	@Mock
	UniqueUser procrssUniqueUserMock;

	@Mock
	ChatUser currChatUserMock;
	@Mock
	ChatUser userForProcessMock;
	
	@Mock
	BannedUser bannedUserMock;
	
	@Mock
	Role roleMock;
	@Mock
	Role processRoleMock;
	
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
		
		
		when(uniqueUserServiceMock.findByName("mockUser")).thenReturn(Optional.of(currUniqueUserMock));
		when(chatUserServiceMock.findByUser(currUniqueUserMock)).thenReturn(Optional.of(currChatUserMock));
	}
	
	
	
	@Test
	@WithMockUser(username = "mockUser")
	public void theONEandONLY_withChatIsPresent() throws Exception {
		
		//getting friend
		String nickFriend = "nickFriend";
		when(chatUserServiceMock.findByNickName(nickFriend)).thenReturn(Optional.of(userForProcessMock));
		
		//getting Chat
		String currentChatID = "1003214";
		Chat chat = new Chat();
		when(chatServiceMock.findById(Long.parseLong(currentChatID))).thenReturn(Optional.of(chat));
		//getting Messages
		List<Message> messageList = List.of();
		when(messageServiceMock.findByChat(chat)).thenReturn(messageList);
		
		
		
		
		mockMVC.perform(post("/addNewFriend")
							.param("nickName", nickFriend)
							.param("currentChatID", currentChatID))
				.andExpectAll(
						model().attribute("friendMessage", nickFriend),
						model().attribute("chatMessages", messageList),
						model().attribute("selectedChat", chat)
						)
				.andExpect(view().name("indexChat"));
		
		
		
		then(chatUserServiceMock).should().addFrindToList(currChatUserMock,userForProcessMock );
		then(chatServiceMock).should().save(any());
		
	}
	
	
	@Test
	@WithMockUser(username = "mockUser")
	public void theONEandONLY_NoChatIsPResent() throws Exception {
		
		//getting friend
		String nickFriend = "nickFriend";
		when(chatUserServiceMock.findByNickName(nickFriend)).thenReturn(Optional.of(userForProcessMock));
		

		mockMVC.perform(post("/addNewFriend")
							.param("nickName", nickFriend)
							.param("currentChatID", ""))	
				.andExpectAll(
						model().attribute("friendMessage", nickFriend),
						model().attributeDoesNotExist("chatMessages"),
						model().attributeDoesNotExist("selectedChat")
						)
				.andExpect(view().name("indexChat"));
		
		
		
		then(chatUserServiceMock).should().addFrindToList(currChatUserMock,userForProcessMock );
		then(chatServiceMock).should().save(any());
		
	}
	
}
