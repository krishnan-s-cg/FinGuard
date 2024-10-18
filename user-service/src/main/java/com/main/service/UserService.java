package com.main.service;

import java.util.List;

import com.main.dto.UserProfile;
import com.main.dto.UserProfileUpdateRequest;
import com.main.dto.UserRegistrationRequest;
import com.main.entity.User;


public interface UserService 
{
	UserProfile addNewUsers(UserRegistrationRequest addUsers);
	List<User> getAllUsers();
	UserProfile updateUserProfile(int userId, UserProfileUpdateRequest user);
	UserProfile getUserById(int userId);
	boolean deleteUser(int userId);
	
}
