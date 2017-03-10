package com.ftn.jan.spring.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.ftn.jan.model.User;
import com.ftn.jan.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private UserService userService;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getPrincipal() + "";
		String password = authentication.getCredentials() + "";

		User user = userService.findByUsername(username);
		if (user == null) {
			throw new BadCredentialsException("1000");
		}

		if (!password.equals(user.getPassword())) {
			throw new BadCredentialsException("1000");
		}

		List<SimpleGrantedAuthority> rights = new ArrayList<SimpleGrantedAuthority>();
		rights.add(new SimpleGrantedAuthority(assignRole(user)));
		
		return new UsernamePasswordAuthenticationToken(username, password,rights);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(
		          UsernamePasswordAuthenticationToken.class);
	}
	
	private static String assignRole(User u){
		
		String role = null;
		
		switch(u.getType()){
		
		case "administrator": role = "ROLE_ADMIN"; break;
		case "pretplatnik": role = "ROLE_SUBSCRIBER"; break;
		default:  throw new IllegalArgumentException("Unsupported ROLE : " + u.getType());
		
		}
		
		return role;
	}

}
