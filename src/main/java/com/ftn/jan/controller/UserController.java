package com.ftn.jan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.jan.model.Category;
import com.ftn.jan.model.User;
import com.ftn.jan.service.UserService;
import com.ftn.jan.viewmodel.ChangePasswordViewModel;
import com.ftn.jan.viewmodel.UserViewModel;

@RestController
@RequestMapping(path="/user")
public class UserController {

	@Autowired
	UserService userService;
	
	
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> list(){
		
		return userService.findAll();
		
	}
	
	@GetMapping("list")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<User> findAll(){
		
		return userService.findAll();
		
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void insert(@RequestBody User user){
		
		userService.save(user);
		
	}
	
	@GetMapping("/{categoryId}")
	public User details(@PathVariable(value="userId") Integer userId) {
		return userService.findByUserId(userId);
	};
	
	@PutMapping
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void update(@RequestBody User user){
		userService.update(user);
	}
	
	@DeleteMapping("/{userId}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void delete(@PathVariable(value="userId") Integer userId) {
		userService.remove(userId);
	};
	
	
	
	@PostMapping("updateProfile")
	public void updateProfile(@RequestBody UserViewModel userViewModel){
		
		userService.updateProfile(userViewModel);
		
	}
	
	@PostMapping("changePassword")
	public void changePassword(@RequestBody ChangePasswordViewModel changePasswordViewModel) throws Exception{
		
		boolean changeSuccess = userService.changePassword(changePasswordViewModel);
		if(!changeSuccess){
			throw new Exception("Cannot change password");
		}
		
		
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
	

	
}
