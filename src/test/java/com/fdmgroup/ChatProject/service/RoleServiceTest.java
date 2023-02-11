package com.fdmgroup.ChatProject.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.ChatProject.model.Role;
import com.fdmgroup.ChatProject.repository.RoleRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RoleServiceTest {

	
@InjectMocks
RoleService roleServiceMock;

@MockBean
RoleRepository roleRepositoryMock;


@Test
@DisplayName("Return Role if searchedRole is there")
public void findByRoleNameTest() {
	String roleName = "Admin";
	Role adminRole = new Role("Admin");
	Optional<Role> adminRoleOpt = Optional.of(adminRole);
	when(roleRepositoryMock.findByRoleName(roleName)).thenReturn(adminRoleOpt);
	
	Role returnedRole = roleServiceMock.findByRoleName(roleName);
	
	verify(roleRepositoryMock,times(1)).findByRoleName(roleName);
	assertEquals(adminRole, returnedRole);
	
}

	
}
