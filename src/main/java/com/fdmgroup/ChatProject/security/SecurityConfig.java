package com.fdmgroup.ChatProject.security;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
	

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private UserDetailsService userDetailsService;//since we are dealing with user objects we have to include this userDetailService
	
	@Autowired
    public SecurityConfig(UserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}

	@Bean
    PasswordEncoder encoder() { // coding our password( string of symbols)
        return new BCryptPasswordEncoder();
	}
    
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(userDetailsService)// we tell spring here don't use your user detail service, but our
			.passwordEncoder(encoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {//HttpSecurity allows us define what parts of website are accesible to particule role
		//or for logged user or let us create custom forms to login. Who can see and do what. 
		http
			.authorizeRequests()
			.antMatchers("/login", "/register", "/error","/h2/**").permitAll()
			//what we can see without login
				//.antMatchers("/css/**", "/js/**", "/h2/**"/*h2 not suitable for production level app*/, "WEB-INF/jsps/**", "/", "/**/*.png", "/register").permitAll()
				// any url with/admin/...something.. user can see if has role ADMIN
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/addUser").hasRole("ADMIN")
				// user has to be authenticated for any different request, so user need to be logged correctly. User doesnt has any particular role,
				//User need only be logged to view anything out. 
				.anyRequest().authenticated()
				.and()
				// here we define our login page our self, so spring doesn't use its login page
				//se we say that for login we want use URL: /login, and everyone can access this login page
			.formLogin().loginPage("/login").permitAll()  
				.defaultSuccessUrl("/indexChat", true)// when we succesfully log in we are landing on this URL page: /  
				// false: if we click the link that user doesn't has acces without login, then after he login he gone to page that he clicked before
				//true: always after login no mattter what url was requested, user land on particular url page, in this case index URL = /
				.failureUrl("/login")//what happen if our authentication gone wrong, here we go back URL : / login page
				.and()
			.logout()
			// delete all information from our user on the session
			//(session is an information that is stored on server for us in order to stay logged in for long period time);
				.logoutSuccessUrl("/")// after logout 
				.invalidateHttpSession(true)
				
				.deleteCookies("JSESSIONID")
				.and()
				// .sessionManagement()
		        // .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		       //  .and()
			.csrf()
				.disable()	// So we don't have to add csrf to every form, using tags on each of our forms
			.httpBasic()
				.and()
			.headers().frameOptions().disable();	// Allows H2 to render correctly
	}
	
	//.hasAuthority -> we need to use "ROLE_" before role so example: "ROLE_ADMIN"
	//.hasRole -> we need to use for example "ADMIN"
	
}

