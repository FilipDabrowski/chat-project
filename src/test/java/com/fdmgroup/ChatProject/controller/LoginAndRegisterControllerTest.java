package com.fdmgroup.ChatProject.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.fdmgroup.ChatProject.ChatProjectApplication;
import com.fdmgroup.ChatProject.model.Chat;
import com.fdmgroup.ChatProject.model.ChatUser;
import com.fdmgroup.ChatProject.model.Message;
import com.fdmgroup.ChatProject.model.Role;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.security.DefaultUniqueUserDetailsService;
import com.fdmgroup.ChatProject.service.BannedUserService;
import com.fdmgroup.ChatProject.service.ChatService;
import com.fdmgroup.ChatProject.service.ChatUserService;
import com.fdmgroup.ChatProject.service.MessageService;
import com.fdmgroup.ChatProject.service.RoleService;
import com.fdmgroup.ChatProject.service.UniqueUserService;
import com.fdmgroup.ChatProject.service.interfaces.IChatService;
import com.fdmgroup.ChatProject.service.interfaces.IChatUserService;
import com.fdmgroup.ChatProject.service.interfaces.IMessageService;
import com.fdmgroup.ChatProject.service.interfaces.IUniqueUserService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@ContextConfiguration(classes = ChatProjectApplication.class)
public class LoginAndRegisterControllerTest {

	@MockBean
	private DefaultUniqueUserDetailsService defaultUniqueUserDetailsService;
	@MockBean
	private PasswordEncoder encoder;
	@MockBean
	private MessageService messageService;
	@MockBean
	private ChatUserService chatUserService;
	@MockBean
	private UniqueUserService uniqueUserService;
	@MockBean
	private ChatService chatService;
	@MockBean
	private RoleService roleService;
	@MockBean
	private BannedUserService bannedUserService;
	@Mock
	private ModelMap model;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser(username = "testuser")
	public void test_GoToIndexChat() throws Exception {
		Optional<UniqueUser> uniqueUserOpt = Optional.of(new UniqueUser());
		uniqueUserOpt.get().setName("testuser");

		Optional<ChatUser> chatUserOpt = Optional.of(new ChatUser());
		chatUserOpt.get().setUser(uniqueUserOpt.get());

		Mockito.when(uniqueUserService.findByName("testuser")).thenReturn(uniqueUserOpt);
		Mockito.when(chatUserService.findByUser(uniqueUserOpt.get())).thenReturn(chatUserOpt);

		mockMvc.perform(get("/indexChat")).andExpect(status().isOk()).andExpect(view().name("indexChat"))
				.andExpect(model().attribute("currentUser", chatUserOpt.get()));
	}

	@WithMockUser(username = "testuser")
	@Test
	public void testRegisterSubmit_Unsuccesfull_Name_exist() throws Exception {
		UniqueUser user = new UniqueUser();
		user.setName("testuser");
		user.setEmailAdress("testuser@email.com");
		user.setPassword("password");

		Role role = new Role();
		role.setRoleName("User");

		when(defaultUniqueUserDetailsService.findByUniqueUserEmile("testuser@email.com")).thenReturn(user);
		when(roleService.findByRoleName("User")).thenReturn(role);
		when(encoder.encode("password")).thenReturn("encoded_password");
		doNothing().when(defaultUniqueUserDetailsService).saveUniqueUser(user);

		mockMvc.perform(post("/register")

				.param("name", "testuser").param("emailAdress", "testuser@email.com").param("password", "password"))
				.andExpect(status().isOk()).andExpect(view().name("register"))
				.andExpect(model().attributeExists("message"));
	}

	@WithMockUser(username = "testuser")
	@Test
	public void testRegisterSubmit_Succesfull() throws Exception {
		UniqueUser user = new UniqueUser();
		user.setName("testuser");
		user.setEmailAdress("testuser@email.com");
		user.setPassword("password");
		
		Role role = new Role();
		role.setRoleName("User");

		when(defaultUniqueUserDetailsService.findByUniqueUserEmile("testuser@email.com")).thenReturn(null);
		when(roleService.findByRoleName("User")).thenReturn(role);
		when(encoder.encode("password")).thenReturn("encoded_password");
		doNothing().when(defaultUniqueUserDetailsService).saveUniqueUser(user);

		mockMvc.perform(post("/register")

				.param("name", "testuser").param("emailAdress", "testuser@email.com").param("password", "password"))
				.andExpect(status().isOk()).andExpect(view().name("index"))
				.andExpect(model().attributeDoesNotExist("message"));
	}
	
	
}
