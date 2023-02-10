package com.fdmgroup.ChatProject.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fdmgroup.ChatProject.model.Role;
import com.fdmgroup.ChatProject.model.UniqueUser;
// wrapper class of UniqueUser
public class UniqueUserPrincipal implements UserDetails {// UserDetails allow us to get informations about user and his account for ex. is he blocked


	
	private UniqueUser uniqueUser;//we dont need Entity annotation because this object is used to authentication purpose only
	
	
	
	public UniqueUserPrincipal(UniqueUser uniqueUser) {
	
	this.uniqueUser = uniqueUser;
}
// if any of overwritten method returns false, the particular user is not going to be able to log in.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {//return a Collection, so it allows here to user has many rules
		Role role = uniqueUser.getRole();
		List<Role> roleList = new ArrayList<>();
		
		roleList.add(role);
		return roleList;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return uniqueUser.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return uniqueUser.getName();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	

	

}
