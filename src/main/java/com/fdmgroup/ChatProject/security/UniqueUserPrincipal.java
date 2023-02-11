package com.fdmgroup.ChatProject.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthoritiesContainer;
import org.springframework.security.core.userdetails.UserDetails;

import com.fdmgroup.ChatProject.model.Role;
import com.fdmgroup.ChatProject.model.UniqueUser;


public class UniqueUserPrincipal implements UserDetails {// wrapper class of UniqueUser

	
	private UniqueUser uniqueUser;//we dont need Entity annotation because this object is used to authentication purpose only
	
	
	
	public UniqueUserPrincipal(UniqueUser uniqueUser) {
	
	this.uniqueUser = uniqueUser;
}
	
	// need for receiving data of authenticated user
		public UniqueUser getUniqueUser() {
			return this.uniqueUser;
		}
// if any of overwritten method returns false, the particular user is not going to be able to log in.
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {//return a Collection, so it allows here to user has many rules
		Role role = uniqueUser.getRole();
		List<Role> roleList = new ArrayList<>();
		roleList.add(role);
		return roleList;
	}

	public void setPassword(String password) {
        this.uniqueUser.setPassword(password);
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

		return !uniqueUser.isLocked();
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
