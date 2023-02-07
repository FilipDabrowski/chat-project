package com.fdmgroup.ChatProject.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fdmgroup.ChatProject.model.UniqueUser;
import com.fdmgroup.ChatProject.service.UniqueUserDetailsService;


@Component
public class UniqueUserValidator implements Validator {
	
	
	
		private final UniqueUserDetailsService uniqueUserDetailsService;
		
		@Autowired
		public UniqueUserValidator(UniqueUserDetailsService singleUserDetailsService) {
		
			this.uniqueUserDetailsService = singleUserDetailsService;
		}

		@Override
		public boolean supports(Class<?> clazz) {
			
			return UniqueUser.class.equals(clazz);
		}

		@Override
		public void validate(Object target, Errors errors) {
			UniqueUser uniqueUser = (UniqueUser) target;
			try {
				uniqueUserDetailsService.loadUserByUsername(uniqueUser.getEmailAdress());
			}catch(UsernameNotFoundException ignored) {
				return;
			}
			errors.rejectValue("name", "","user with this name exists");
			
		}

	}


