package com.fdmgroup.ChatProject.controller;



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
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.Role;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.service.BannedUserService;
import com.fdmgroup.ChatProject.service.ChatUserService;
import com.fdmgroup.ChatProject.service.RoleService;
import com.fdmgroup.ChatProject.service.UniqueUserService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;




@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ChatProjectApplication.class})
public class BlockAndBanControllerTest {

	
	@MockBean
	private RoleService roleServiceMock;
	@MockBean
	private UniqueUserService uniqueUserServiceMock;
	@MockBean
	private ChatUserService chatUserServiceMock;
	@MockBean
	private BannedUserService bannedUserServiceMock;
		
	@Autowired
	private MockMvc mockMVC;
	
	//Mocks
	
//	@MockBean
//	ModelMap modelMapMock;
	
	
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
	public void blockUserTest() throws Exception {
		
		String nickName = "nickName";	
		when(chatUserServiceMock.findByNickName(nickName)).thenReturn(Optional.of(userForProcessMock));

		
	//	when(roleServiceMock.findByRoleName("Admin")).thenReturn(roleMock);
			
		mockMVC.perform(post("/blockUser")
				.param("nickName", nickName))
				.andExpect(status().isOk())
				.andExpectAll(
						model().attribute("currentUser", currChatUserMock),
						model().attributeDoesNotExist("bannedUsers"))
				.andExpect(view().name("profileSetting"));
		
		
		then(chatUserServiceMock).should().addUserToBlockedList(userForProcessMock, currChatUserMock);	
	}
	

	@Test
	@WithMockUser(username = "mockUser")
	public void banUserTest() throws Exception {
		String nickName = "nickName";	
		
		when(chatUserServiceMock.findByNickName(nickName)).thenReturn(Optional.of(userForProcessMock));
			
		//to return to the Admin SettingPage
		when(roleServiceMock.findByRoleName("Admin")).thenReturn(roleMock);
		
		
		List<BannedUser> bannedUserList = new ArrayList<>();
		when(bannedUserServiceMock.findAll()).thenReturn(bannedUserList);
		
		
		mockMVC.perform(post("/banUser")
				.param("nickName", nickName))
				.andExpect(status().isOk())
				.andExpectAll(
						model().attribute("currentUser", currChatUserMock),
						model().attribute("bannedUsers",bannedUserList))
				.andExpect(view().name("/admin/allSetting"));
		
		then(procrssUniqueUserMock).should().setLocked(true);
		then(bannedUserServiceMock).should().save(any());
	}
	
	@Test
	@WithMockUser(username = "mockUser")
	public void unBanUserTest() throws Exception {
		String nickName = "nickName";	
		
		when(chatUserServiceMock.findByNickName(nickName)).thenReturn(Optional.of(userForProcessMock));
		
		when(bannedUserMock.getBannedUser()).thenReturn(userForProcessMock);
		
		when(bannedUserServiceMock.findById(1)).thenReturn(Optional.of(bannedUserMock));
		
		//to return to the Admin SettingPage
		when(roleServiceMock.findByRoleName("Admin")).thenReturn(roleMock);
		
		
		List<BannedUser> bannedUserList = new ArrayList<>();
		when(bannedUserServiceMock.findAll()).thenReturn(bannedUserList);
		
		
		mockMVC.perform(post("/unBan/1")
				.param("nickName", nickName))
				.andExpect(status().isOk())
				.andExpectAll(
						model().attribute("currentUser", currChatUserMock),
						model().attribute("bannedUsers",bannedUserList))
				.andExpect(view().name("/admin/allSetting"));
		
		then(procrssUniqueUserMock).should().setLocked(false);
	}
	

	@Test
	@WithMockUser(username = "mockUser")
	public void unBlockUserTest() throws Exception {
		String nickName = "nickName";	
		
		when(chatUserServiceMock.findByNickName(nickName)).thenReturn(Optional.of(userForProcessMock));
		
		
		when(chatUserServiceMock.findById(1)).thenReturn(Optional.of(userForProcessMock));
		
		//to return to the Admin SettingPage
		when(roleServiceMock.findByRoleName("Admin")).thenReturn(roleMock);
		
		
		List<BannedUser> bannedUserList = new ArrayList<>();
		when(bannedUserServiceMock.findAll()).thenReturn(bannedUserList);
		
		
		mockMVC.perform(post("/unBlock/1")
				.param("nickName", nickName))
				.andExpect(status().isOk())
				.andExpectAll(
						model().attribute("currentUser", currChatUserMock),
						model().attribute("bannedUsers",bannedUserList))
				.andExpect(view().name("/admin/allSetting"));
		
		then(chatUserServiceMock).should().unblockUser(userForProcessMock, currChatUserMock);
	}	
	
	@Test
	@WithMockUser(username = "mockUser")
	public void deleteFriendTest() throws Exception {
		String nickName = "nickName";	
		
		when(chatUserServiceMock.findByNickName(nickName)).thenReturn(Optional.of(userForProcessMock));
		
		
		when(chatUserServiceMock.findById(1)).thenReturn(Optional.of(userForProcessMock));
		
		//to return to the Admin SettingPage
		when(roleServiceMock.findByRoleName("Admin")).thenReturn(roleMock);
		
		
		List<BannedUser> bannedUserList = new ArrayList<>();
		when(bannedUserServiceMock.findAll()).thenReturn(bannedUserList);
		
		
		mockMVC.perform(post("/deletFriend/1")
				.param("nickName", nickName))
				.andExpect(status().isOk())
				.andExpectAll(
						model().attribute("currentUser", currChatUserMock),
						model().attribute("bannedUsers",bannedUserList))
				.andExpect(view().name("/admin/allSetting"));
		
		then(chatUserServiceMock).should().removeFriend(userForProcessMock, currChatUserMock);
	}		
	
	
	
}
