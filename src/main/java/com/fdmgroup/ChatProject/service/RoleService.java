package com.fdmgroup.ChatProject.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fdmgroup.ChatProject.model.Role;
import com.fdmgroup.ChatProject.repository.RoleRepository;
import com.fdmgroup.ChatProject.service.interfaces.IRoleService;
@Service
public class RoleService implements IRoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	

	@Override
	public Role findByRoleName(String roleName) {
		Optional<Role> optRole = roleRepository.findByRoleName(roleName);
		
		return optRole.orElse(new Role("default role"));
	}

}
