package com.ftn.jan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.jan.model.User;
import com.ftn.jan.service.UserService;
import com.ftn.jan.viewmodel.ChangePasswordViewModel;
import com.ftn.jan.viewmodel.UserViewModel;

@RestController
@RequestMapping(path="/user")
public class UserController {

	@Autowired
	UserService userService;
	
	
	@GetMapping("list")
	public List<User> findAll(){
		
		return userService.findAll();
		
	}
	
	@PostMapping("updateProfile")
	public void updateProfile(@RequestBody UserViewModel userViewModel){
		
		userService.updateProfile(userViewModel);
		
	}
	
	@PostMapping("changePassword")
	public String changePassword(@RequestBody ChangePasswordViewModel changePasswordViewModel){
		
		boolean changeSuccess = userService.changePassword(changePasswordViewModel);
		
		
		return "";
	}
	
	
	@RequestMapping(path="login",method = RequestMethod.POST)
	@ResponseBody
	public String login(@RequestBody User u){

		User potentialUser = userService.login(u.getUsername(),u.getPassword());
		
		if(potentialUser!=null){
			return potentialUser.toString();
		}else{
			return "Invalid username or password";
		}
		
		
	}
	
	@RequestMapping(method = RequestMethod.GET)
	//@ResponseBody
	@PreAuthorize("hasRole('USER')")
	public String changePassword(){
		userService.changePassword("jkdjlsd");
		return "dasdkasd";
	}
	
	//Testing roles
	@GetMapping(value="/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String adminTest(){
		return "Only admin can see this!";
	}
	
	@GetMapping(value="/sub")
	@PreAuthorize("hasRole('SUBSCRIBER')")
	public String subTest(){
		return "Only subscriber can see this!";
	}
	
	@GetMapping(value="/auth")
	@PreAuthorize("hasAnyRole('SUBSCRIBER','ADMIN')")
	public String authTest(){
		return "Only authenticated user can see this!";
	}
	
	@GetMapping(value="/open")
	public String openTest(){
		return "Anybody can see this!";
	}
	
}
