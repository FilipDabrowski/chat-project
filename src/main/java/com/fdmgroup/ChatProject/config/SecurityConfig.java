package com.fdmgroup.ChatProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.fdmgroup.ChatProject.security.AuthProviderImpl;





@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	

		private final AuthProviderImpl authProvider;
		
		
		@Autowired
		public SecurityConfig(AuthProviderImpl authProvider) {
			
			this.authProvider = authProvider;
		}
		@Override
		protected void configure(HttpSecurity http) throws Exception{
			// configure Spring Security (not default login page )
			// configure roles
			
			http.csrf().disable()// disable tokenization
			.authorizeRequests()
			.antMatchers("/**").permitAll()
//			.antMatchers("/admin").hasRole("ADMIN")
//			.antMatchers("/authorize").hasAnyRole("SIMPLE_USER","ADMIN")
//			.antMatchers("/login", "/registration", "/error").permitAll()
//			.antMatchers("/login", "/registration", "/error", "/change-password", "/css/**").permitAll()
//			.antMatchers("/**/*.js", "/**/*.css").permitAll()
			.anyRequest().hasAnyRole("SIMPLE_USER","ADMIN")
			.and()
			.formLogin().loginPage("/login")
			.loginProcessingUrl("/process_login")
			.defaultSuccessUrl("/indexChat", true)
			.failureUrl("/login?error")
			.and()
			.csrf()
			.disable()
			.logout()
			.logoutUrl("/logout")
			.logoutSuccessUrl("/auth/login");

		}

		// setting the authentication
		protected void configure(AuthenticationManagerBuilder auth) {
			auth.authenticationProvider(authProvider);

	}
		
	}
	

