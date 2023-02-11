package com.fdmgroup.ChatProject.controller;

import java.util.ArrayList;
import java.util.Collection;
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
import com.fdmgroup.ChatProject.model.Role;
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.service.BannedUserService;
import com.fdmgroup.ChatProject.service.ChatUserService;
import com.fdmgroup.ChatProject.service.RoleService;
import com.fdmgroup.ChatProject.service.UniqueUserService;
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

public class UserControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatUserService chatUserService;

    @MockBean
    private UniqueUserService uniqueUserService;

    @MockBean
    private RoleService roleService;

    @MockBean
    private BannedUserService bannedUserService;

	@Mock
	private ModelMap model;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Mock
	Authentication authentication;
//	@MockBean
//	private PasswordEncoder passwordEncoder;
	
@Test
@WithMockUser(roles = "Admin")
public void test_editProfile_for_admin_role() throws Exception {
	
	 Long id = 1L;
     String nickName = "newNickName";
     String name = "newName";
     String emailAdress = "newEmail@example.com";
     String roleName = "Admin";
     ChatUser chatUser = new ChatUser();
     UniqueUser uniqueUser = new UniqueUser();
     Role role = new Role();
     
     role.setRoleName(roleName);
    uniqueUser.setRole(role);
     chatUser.setUser(uniqueUser);
     chatUser.setNickName(nickName);
     uniqueUser.setEmailAdress(emailAdress);
     
     when(chatUserService.findById(id)).thenReturn(java.util.Optional.of(chatUser));
     when(roleService.findByRoleName(roleName)).thenReturn(role);
     when(model.addAttribute("currenUser", chatUser)).thenReturn(model);
     mockMvc.perform(post("/editProfile/{id}", id)
             .param("nickName", nickName)
             .param("name", name)
             .param("emailAdress", emailAdress))
             .andExpect(status().isOk())
     .andExpect(model().attribute("currentUser", chatUser))
     .andExpect(view().name("/admin/allSetting"));
		

	
	
}

@Test
@WithMockUser(roles = "User")
public void test_editProfile_for_user_role() throws Exception {
	
	 Long id = 1L;
     String nickName = "newNickName";
     String name = "newName";
     String emailAdress = "newEmail@example.com";
     String roleName = "User";
     ChatUser chatUser = new ChatUser();
     UniqueUser uniqueUser = new UniqueUser();
     Role role = new Role();
     
     role.setRoleName(roleName);
    uniqueUser.setRole(role);
     chatUser.setUser(uniqueUser);
     chatUser.setNickName(nickName);
     uniqueUser.setEmailAdress(emailAdress);
     
     when(chatUserService.findById(id)).thenReturn(java.util.Optional.of(chatUser));
     when(roleService.findByRoleName(roleName)).thenReturn(role);
     when(model.addAttribute("currenUser", chatUser)).thenReturn(model);
     mockMvc.perform(post("/editProfile/{id}", id)
             .param("nickName", nickName)
             .param("name", name)
             .param("emailAdress", emailAdress))
             .andExpect(status().isOk())
     .andExpect(model().attribute("currentUser", chatUser))
     .andExpect(view().name("profileSetting"));
		
}
@Test
@WithMockUser

public void test_changePassword() throws Exception{
	Long id = 100L;
    String oldPassword = "oldPassword";
    String newPassword = "newPassword";
    String confirmNewPassword = "confirmNewPassword";
 
    				
    ChatUser chatUser = new ChatUser();
    
    UniqueUser uniqueUser = new UniqueUser();
    //uniqueUser.setRole(new Role("Admin"));
    
    uniqueUser.setPassword(passwordEncoder.encode(oldPassword));
    chatUser.setUser(uniqueUser);
    
	

	when(chatUserService.findById(id)).thenReturn(java.util.Optional.of(chatUser));
	
	mockMvc.perform(post("/changePassword/{id}", id)
            .param("oldPassword", oldPassword)
            .param("newPassword", newPassword)
            .param("confirmNewPassword", confirmNewPassword))
            .andExpect(status().isOk());

    		 verify(uniqueUserService, times(1)).save(uniqueUser);


}

@Test
@WithMockUser(username="testUser", password="testPassword")
public void goToProfileSettings_UserPresent_ReturnsProfileSettingView() throws Exception {
  UniqueUser uniqueUser = new UniqueUser();
  ChatUser chatUser = new ChatUser();
  uniqueUser.setRole(new Role("User"));
  chatUser.setUser(uniqueUser);
 
  Optional<ChatUser> chatUserOpt = Optional.of(chatUser);
  
  when(authentication.getName()).thenReturn("testUser");
  when(uniqueUserService.findByName("testUser")).thenReturn(Optional.of(uniqueUser));
  when(chatUserService.findByUser(uniqueUser)).thenReturn(chatUserOpt);
  when(roleService.findByRoleName("User")).thenReturn(new Role("User"));
  
  mockMvc.perform(get("/settings"))
  .andExpect(status().isOk())
  .andExpect(view().name("profileSetting"));

}

@Test
@WithMockUser(username="testAdmin", password="testPassword")
public void goToProfileSettings_AdminPresent_ReturnsProfileSettingView() throws Exception {
  UniqueUser uniqueUser = new UniqueUser();
  ChatUser chatUser = new ChatUser();
  uniqueUser.setRole(new Role("Admin"));
  chatUser.setUser(uniqueUser);
 
  Optional<ChatUser> chatUserOpt = Optional.of(chatUser);
  
  when(authentication.getName()).thenReturn("testAdmin");
  when(uniqueUserService.findByName("testAdmin")).thenReturn(Optional.of(uniqueUser));
  when(chatUserService.findByUser(uniqueUser)).thenReturn(chatUserOpt);
  when(roleService.findByRoleName("Admin")).thenReturn(new Role("Admin"));
  
  mockMvc.perform(get("/settings"))
  .andExpect(status().isOk())
  .andExpect(view().name("/admin/allSetting"));

}


}

