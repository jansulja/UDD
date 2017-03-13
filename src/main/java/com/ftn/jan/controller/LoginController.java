package com.ftn.jan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.jan.model.User;
import com.ftn.jan.service.UserService;

@RestController
@RequestMapping("login")
public class LoginController {

	@Autowired
	UserService userService;
	
//	@PostMapping
//	public User  login(@RequestBody User u){
//		
//		User potentialUser = userService.login(u.getUsername(), u.getPassword());
//		if(potentialUser!=null){
//			return potentialUser;
//		}else{
//			throw new BadCredentialsException("Invalid username or password.");
//		}
//		
//		
//	}
	
	@GetMapping("current")
	public User getCurrentUser(){
		
		User u = userService.getLoggedInUser();
		return u;
		
		
	}

//	@GetMapping("logout")
//	public void logout(){
//		userService.logout();
//	}
	
}
