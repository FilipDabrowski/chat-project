//package com.fdmgroup.ChatProject.security;
//
//
//import java.util.Collection;
//import java.util.Collections;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.fdmgroup.ChatProject.model.UniqueUser;
//
//
//public class UniqueUserDetails implements UserDetails {
//	
//
//	
//	
//		private final UniqueUser uniqueUser;
//		
//		
//		
//
//		public UniqueUserDetails(UniqueUser uniqueUser) {
//			this.uniqueUser = uniqueUser;
//		}
//		
//		
//
//		@Override
//		public Collection<? extends GrantedAuthority> getAuthorities() {
//			return Collections.singletonList(new SimpleGrantedAuthority(uniqueUser.getRole()));	}
//
//		@Override
//		public String getPassword() {
//			System.out.println("Single user details / getPassword");
//			return this.uniqueUser.getPassword();
//		}
//		
//		public void setPassword(String password) {
//	        this.uniqueUser.setPassword(password);
//	    }
//
//		@Override
//		public String getUsername() {
//			
//			return this.uniqueUser.getName();
//		}
//		
//		public String getEmail() {
//			return this.uniqueUser.getEmailAdress();
//		}
//
//		@Override
//		public boolean isAccountNonExpired() {
//			// TODO Auto-generated method stub
//			return true;
//		}
//
//		@Override
//		public boolean isAccountNonLocked() {
//			// TODO Auto-generated method stub
//			return true;
//		}
//
//		@Override
//		public boolean isCredentialsNonExpired() {
//			// TODO Auto-generated method stub
//			return true;
//		}
//
//		@Override
//		public boolean isEnabled() {
//			// TODO Auto-generated method stub
//			return true;
//		}
//		
//		
//		// need for receiving data of authenticated user
//		public UniqueUser getUniqueUser() {
//			return this.uniqueUser;
//		}
//		
//	     
//	
//}
