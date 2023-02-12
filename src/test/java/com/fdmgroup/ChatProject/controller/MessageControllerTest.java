package com.fdmgroup.ChatProject.controller;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.withSettings;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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


public class MessageControllerTest {

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
	    @WithMockUser(username = "user", password = "password", roles = "User")
	    public void testSendMessageToChat() throws Exception {
	    	
	    	String message = "Hello";
	        String chatID = "1";
	        String name = "user";
	        String password = "sss";
	        
	        UniqueUser uniqueUser = new UniqueUser(passwordEncoder.encode(password),"user","aaa@aaa.com",new Role("User"));
	        ChatUser chatUser = new ChatUser(name, uniqueUser);
	        Chat chat = new Chat("Test Chat", chatUser, chatUser);
	        chat.setId(Long.parseLong(chatID));
	        
	        Message sendMessage = new Message(message, chatUser, chat);
	        
	        Optional<UniqueUser> uniqueUserOpt = Optional.of(uniqueUser);
	        Optional<ChatUser> chatUserOpt = Optional.of(chatUser);
	        Optional<Chat> currChatOpt = Optional.of(chat);
	        
	        when(uniqueUserService.findByName(name)).thenReturn(uniqueUserOpt);
	        when(chatUserService.findByUser(uniqueUser)).thenReturn(chatUserOpt);
	        when(chatService.findById(Long.parseLong(chatID))).thenReturn(currChatOpt);
	        when(messageService.findByChat(chat)).thenReturn(Collections.singletonList(sendMessage));
	        
	        mockMvc.perform(post("/sendMessage")
	          .param("message", message)
	          .param("currentChatID", chatID))
	        .andExpect(status().isOk())
	        .andExpect(model().attribute("selectedChat", chat))
	      .andExpect(model().attribute("chatMessages", Collections.singletonList(sendMessage)))
	      .andExpect(model().attribute("currentUser", chatUser))
	       .andExpect(view().name("indexChat"));
	        
	       verify(uniqueUserService, times(1)).findByName(name);
	       verify(chatUserService, times(1)).findByUser(uniqueUser);
	       verify(chatService, times(1)).findById(Long.parseLong(chatID));
	      verify(messageService, times(1)).save(any());// how to ????? 
	      verify(messageService, times(1)).findByChat(chat);
	      }
	}

