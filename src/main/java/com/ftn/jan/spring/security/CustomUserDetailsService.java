/**
 * 
 */
package com.ftn.jan.spring.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ftn.jan.jpa.repository.UserRepository;
import com.ftn.jan.model.User;


@Component
public class CustomUserDetailsService implements UserDetailsService
{
	@Autowired UserRepository repo;
	
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException
	{
		User user = repo.findByUsername(username);
		System.out.println("Username: "+username+", User: "+user);
		if(user == null) throw new UsernameNotFoundException("User not found");
		return new SecurityUser(user);
	}

}
