package com.fdmgroup.ChatProject.security;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fdmgroup.ChatProject.service.UniqueUserDetailsService;


@Component

public class AuthProviderImpl implements AuthenticationProvider {

		
		private final UniqueUserDetailsService singleUserDetailsService;
		

		
		//@Autowired
		public AuthProviderImpl(UniqueUserDetailsService singleUserDetailsService) {
			
			this.singleUserDetailsService = singleUserDetailsService;
			
		}


		@Override
		public Authentication authenticate(Authentication authentication) throws AuthenticationException {
			System.out.println("Auteticate method/ AuthProviderimpl start");
			//String name = authentication.getName();
			String email = authentication.getName();
			singleUserDetailsService.setAuthenticationObject(authentication);
			
			UserDetails singleUserDetails = singleUserDetailsService.loadUserByUsername(email);
			
			String password = authentication.getCredentials().toString();
			
			Integer hashedPassword = password.hashCode();
			String hashedPasswordString = hashedPassword.toString();
			
			/*if(!password.equals(singleUserDetails.getPassword()))
				throw new BadCredentialsException("Incorrect password");
			
			// the empty list is temporary (instead of the user role)
			// UsernamePasswordAuthenticationToken return a our authentication object with password and role)
			return new UsernamePasswordAuthenticationToken(singleUserDetails, password,Collections.emptyList());
		}*/
			
			if(!hashedPasswordString.equals(singleUserDetails.getPassword()))
				throw new BadCredentialsException("Incorrect password");
			
			// the empty list is temporary (instead of the user role)
			// UsernamePasswordAuthenticationToken return a our authentication object with password and role)
			return new UsernamePasswordAuthenticationToken(singleUserDetails, password,singleUserDetails.getAuthorities());
		}

		
		// if we have more than one Authentication Providers there we can choose scenario where to use this provider
		@Override
		//aClass instead of authentication
		public boolean supports(Class<?> aClass) {
			// TODO Auto-generated method stub
			return true;
		}
		
		/// added for indian guy 
		/*private Set<SimpleGrantedAuthority> getAuthorities(Set<Authority> authorities){
			Set<SimpleGrantedAuthority> list = new HashSet<>();
			for(Authority auth:authorities) {
				list.add(new SimpleGrantedAuthority(auth.getAuthority()));
			}
			
			return list;
		}*/

	}



