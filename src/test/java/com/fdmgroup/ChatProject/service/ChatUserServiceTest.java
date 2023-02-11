package com.fdmgroup.ChatProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Collection;
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
import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.repository.ChatUserRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ChatUserServiceTest {

	
	@InjectMocks
	ChatUserService chatUserServiceMock;
	
	@MockBean
	ChatUserRepository chatUserRepositoryMock;
	
	
	
	@Test
	public void saveTest() {
		ChatUser chatUser = new ChatUser();
		
		chatUserServiceMock.save(chatUser);
		
		verify(chatUserRepositoryMock,times(1)).save(chatUser);
	}
	
	@Test
	public void findByIdTest() {
		long chatUserID = 1L; 
		ChatUser chatUser = new ChatUser();
		Optional<ChatUser> chatUserOpt = Optional.of(chatUser);
		when(chatUserRepositoryMock.findById(chatUserID)).thenReturn(chatUserOpt);
		
		Optional<ChatUser> returnedChatUserOpt = chatUserServiceMock.findById(chatUserID);
		
		verify(chatUserRepositoryMock, times(1)).findById(chatUserID);
		assertEquals(chatUserOpt, returnedChatUserOpt);
	}
	
	
	@Test
	public void findByNickNameTest() {
		String nickName = "nickName"; 
		ChatUser chatUser = new ChatUser();
		Optional<ChatUser> chatUserOpt = Optional.of(chatUser);
		when(chatUserRepositoryMock.findByNickName(nickName)).thenReturn(chatUserOpt);
		
		Optional<ChatUser> returnedChatUserOpt = chatUserServiceMock.findByNickName(nickName);
		
		verify(chatUserRepositoryMock, times(1)).findByNickName(nickName);
		assertEquals(chatUserOpt, returnedChatUserOpt);
	}
	
	@Test
	public void findByUserTest_ChatUserIsPresent() {
		UniqueUser uniqueUser = new UniqueUser(); 
		ChatUser chatUser = new ChatUser();
		Optional<ChatUser> chatUserOpt = Optional.of(chatUser);
		
		List<ChatUser> chatUserList = List.of(chatUser);
		
		when(chatUserRepositoryMock.findByUser(uniqueUser)).thenReturn(chatUserList);
		
		
		Optional<ChatUser> returnedChatUserOpt = chatUserServiceMock.findByUser(uniqueUser);
		
		
		verify(chatUserRepositoryMock, times(1)).findByUser(uniqueUser);
		assertEquals(chatUserOpt, returnedChatUserOpt);
	}
	
	@Test
	public void findByUserTest_noChatUserPresent() {
		UniqueUser uniqueUser = new UniqueUser(); 
		ChatUser chatUser = new ChatUser();
		Optional<ChatUser> chatUserOpt = Optional.of(chatUser);
		
		List<ChatUser> chatUserList = List.of();
		
		when(chatUserRepositoryMock.findByUser(uniqueUser)).thenReturn(chatUserList);
		
		
		Optional<ChatUser> returnedChatUserOpt = chatUserServiceMock.findByUser(uniqueUser);
		
		
		verify(chatUserRepositoryMock, times(1)).findByUser(uniqueUser);
		assertEquals(Optional.empty(), returnedChatUserOpt);
	}
	
	
	
	@Test
	public void addFrindToListTest_newFriend() {
		
		ChatUser chatUserMock = mock(ChatUser.class);
		ChatUser friend = new ChatUser();
		
		Collection<ChatUser> chatUserListMock = mock(Collection.class);
		
		when(chatUserMock.getFriendList()).thenReturn(chatUserListMock);
		when(chatUserListMock.contains(friend)).thenReturn(false);
		
		chatUserServiceMock.addFrindToList(chatUserMock, friend);
		

		verify(chatUserListMock,times(1)).add(friend);
		verify(chatUserRepositoryMock,times(1)).save(chatUserMock);
	}
	
	@Test
	public void addFrindToListTest_FriendAlreadyThere() {
		
		ChatUser chatUserMock = mock(ChatUser.class);
		ChatUser friend = new ChatUser();
		
		Collection<ChatUser> chatUserListMock = mock(Collection.class);
				
		when(chatUserMock.getFriendList()).thenReturn(chatUserListMock);
		when(chatUserListMock.contains(friend)).thenReturn(true);
		
		chatUserServiceMock.addFrindToList(chatUserMock, friend);

		

		verify(chatUserListMock,times(0)).add(friend);
		verify(chatUserRepositoryMock,times(0)).save(chatUserMock);
	}
	
	
	@Test
	public void addChatToUserTest_newChat() {
		
		ChatUser chatUserMock = mock(ChatUser.class);
		Chat chat = new Chat();
		
		Collection<Chat> chatListMock = mock(Collection.class);
		
		when(chatUserMock.getChats()).thenReturn(chatListMock);
		when(chatListMock.contains(chat)).thenReturn(false);
		
		chatUserServiceMock.addChatToUser(chatUserMock, chat);
		

		verify(chatListMock,times(1)).add(chat);
		verify(chatUserRepositoryMock,times(1)).save(chatUserMock);
	}
	
	@Test
	public void addChatToUserTest_ChatAlreadyThere() {
		
		ChatUser chatUserMock = mock(ChatUser.class);
		Chat chat = new Chat();
		
		Collection<Chat> chatListMock = mock(Collection.class);
		
		when(chatUserMock.getChats()).thenReturn(chatListMock);
		when(chatListMock.contains(chat)).thenReturn(true);
		
		chatUserServiceMock.addChatToUser(chatUserMock, chat);

		

		verify(chatListMock,times(0)).add(chat);
		verify(chatUserRepositoryMock,times(0)).save(chatUserMock);
	}
	
	
	@Test
	public void addUserToBlockedListTest_newChatUser() {
		
		ChatUser chatUserMock = mock(ChatUser.class);
		ChatUser toBlock = new ChatUser();
		
		Collection<ChatUser> chatUserListMock = mock(Collection.class);
		
		when(chatUserMock.getBlockedUsers()).thenReturn(chatUserListMock);
		when(chatUserListMock.contains(toBlock)).thenReturn(false);
		
		chatUserServiceMock.addUserToBlockedList(toBlock,chatUserMock);
		

		verify(chatUserListMock,times(1)).add(toBlock);
		verify(chatUserRepositoryMock,times(1)).save(chatUserMock);
	}
	
	
	@Test
	public void addUserToBlockedListTest_ChatUserAlreadyThere() {
		
		ChatUser chatUserMock = mock(ChatUser.class);
		ChatUser toBlock = new ChatUser();
		
		Collection<ChatUser> chatUserListMock = mock(Collection.class);
		
		when(chatUserMock.getBlockedUsers()).thenReturn(chatUserListMock);
		when(chatUserListMock.contains(toBlock)).thenReturn(true);
		
		chatUserServiceMock.addUserToBlockedList(toBlock,chatUserMock);
		
		verify(chatUserListMock,times(0)).add(toBlock);
		verify(chatUserRepositoryMock,times(0)).save(chatUserMock);
	}
	
	
	
	@Test
	public void unblockUserTest_user_Is_Blocked() {
		ChatUser chatUserMock = mock(ChatUser.class);
		ChatUser toUnBlock = new ChatUser();
		
		Collection<ChatUser> chatUserListMock = mock(Collection.class);
		
		when(chatUserMock.getBlockedUsers()).thenReturn(chatUserListMock);
		when(chatUserListMock.contains(toUnBlock)).thenReturn(true);
		
		chatUserServiceMock.unblockUser(toUnBlock, chatUserMock);
		
		verify(chatUserListMock,times(1)).remove(toUnBlock);
		verify(chatUserRepositoryMock,times(1)).save(chatUserMock);
	}
	
	@Test
	public void unblockUserTest_user_IsNOT_Blocked() {
		ChatUser chatUserMock = mock(ChatUser.class);
		ChatUser toUnBlock = new ChatUser();
		
		Collection<ChatUser> chatUserListMock = mock(Collection.class);
		
		when(chatUserMock.getBlockedUsers()).thenReturn(chatUserListMock);
		when(chatUserListMock.contains(toUnBlock)).thenReturn(false);
		
		chatUserServiceMock.unblockUser(toUnBlock, chatUserMock);
		
		verify(chatUserListMock,times(0)).remove(toUnBlock);
		verify(chatUserRepositoryMock,times(0)).save(chatUserMock);
		
	}
	
	
	@Test
	public void removeFriendTest_user_Is_Fiend() {
		ChatUser chatUserMock = mock(ChatUser.class);
		ChatUser toUnFriend = new ChatUser();
		
		Collection<ChatUser> chatUserListMock = mock(Collection.class);
		
		when(chatUserMock.getFriendList()).thenReturn(chatUserListMock);
		when(chatUserListMock.contains(toUnFriend)).thenReturn(true);
		
		chatUserServiceMock.removeFriend(toUnFriend, chatUserMock);
		
		verify(chatUserListMock,times(1)).remove(toUnFriend);
		verify(chatUserRepositoryMock,times(1)).save(chatUserMock);
	}
	
	@Test
	public void removeFriendTest_user_IsNOT_Friend() {
		ChatUser chatUserMock = mock(ChatUser.class);
		ChatUser toUnFriend = new ChatUser();
		
		Collection<ChatUser> chatUserListMock = mock(Collection.class);
		
		when(chatUserMock.getFriendList()).thenReturn(chatUserListMock);
		when(chatUserListMock.contains(toUnFriend)).thenReturn(false);
		
		chatUserServiceMock.removeFriend(toUnFriend, chatUserMock);
		
		verify(chatUserListMock,times(0)).remove(toUnFriend);
		verify(chatUserRepositoryMock,times(0)).save(chatUserMock);
		
	}
	
	
	
	
}
