package com.ftn.jan.service.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.ftn.jan.jpa.repository.UserRepository;
import com.ftn.jan.model.User;
import com.ftn.jan.service.UserService;
import com.ftn.jan.viewmodel.ChangePasswordViewModel;
import com.ftn.jan.viewmodel.UserViewModel;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}


	@Override
	public void insertUser(User u) {
		// TODO Auto-generated method stub
		
	}


	//Returns User object if on login success, otherwise returns null
	@Override
	public User login(String username, String password) {
		
		User user = userRepository.findByUsername(username);
		
		if(user!=null && user.getPassword().equals(password)){
			SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(username, password));
			return user;
		}else{
			return null;
		}
		
		
	}


	@Override
	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}


	@Override
	public void changePassword(String newPassword) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>)    SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		authorities.forEach(System.out::println);
		System.out.println("User: " + currentPrincipalName);
		
	}


	@Override
	public boolean changePassword(ChangePasswordViewModel changePasswordViewModel) throws Exception {
		// TODO Auto-generated method stub
		boolean valid = true;
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		
		User currentUser = this.findByUsername(currentPrincipalName);
		
		if(!currentUser.getPassword().equals(changePasswordViewModel.getOldPassword())){
			throw new Exception("Invalid password");
		}
		
		if(!changePasswordViewModel.getNewPassword().equals(changePasswordViewModel.getConfirmPassword())){
			throw new Exception("New password and confirm password does not match");
		}
		
		currentUser.setPassword(changePasswordViewModel.getNewPassword());
		userRepository.save(currentUser);
		
		SecurityContextHolder.clearContext();
		
		return valid;
	}


	@Override
	public void updateProfile(UserViewModel userViewModel) {

		User u = getCurrentUser();
		u.setFirstname(userViewModel.getFirstname());
		u.setLastname(userViewModel.getLastname());		
		userRepository.save(u);

	}
	
	private User getCurrentUser(){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		User currentUser = this.findByUsername(currentPrincipalName);
		return currentUser;
	}


	@Override
	public User getLoggedInUser() {
		return getCurrentUser();
	}


	@Override
	public void logout() {
		// TODO Auto-generated method stub
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

	}



	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		userRepository.save(user);
	}


	@Override
	public User findByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return userRepository.findOne(userId);
	}


	@Override
	public void save(User user) {
		
		userRepository.save(user);
		
	}


	@Override
	public void remove(Integer userId) {
		// TODO Auto-generated method stub
		userRepository.delete(userId);
	}
	
	

}
