package com.fdmgroup.ChatProject.service.interfaces;

import java.util.Optional;

import com.fdmgroup.ChatProject.model.Role;

public interface IRoleService {
	Role findByRoleName(String roleName);
}
