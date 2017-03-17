package com.ftn.jan.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ftn.jan.jpa.repository.UserRepository;
import com.ftn.jan.model.User;
import com.ftn.jan.viewmodel.ChangePasswordViewModel;
import com.ftn.jan.viewmodel.UserViewModel;


public interface UserService {

	public void insertUser(User u);
	public List<User> findAll();
	public User login(String username,String password);
	public User getLoggedInUser();
	public User findByUsername(String username);
	public void changePassword(String newPassword);
	public boolean changePassword(ChangePasswordViewModel changePasswordViewModel);
	public void updateProfile(UserViewModel userViewModel);
	public void logout();
	public User findByUserId(Integer userId);
	public void update(User user);
	public void save(User user);
	public void remove(Integer userId);
	
	
}
