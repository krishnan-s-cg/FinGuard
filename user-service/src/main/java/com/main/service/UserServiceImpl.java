package com.main.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.UserProfile;
import com.main.dto.UserProfileUpdateRequest;
import com.main.dto.UserRegistrationRequest;
import com.main.entity.User;
import com.main.exception.UserNotFoundException;
import com.main.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepo;
 
	@Override
	public UserProfile addNewUsers(UserRegistrationRequest addUsers) 
	{
		User user = new User();
		user.setUserName(addUsers.getUserName());
		user.setPassword(addUsers.getPassword());
        user.setEmail(addUsers.getEmail());
        user.setRole(addUsers.getRole());
        
        User savedUser = userRepo.save(user);
        
        return new UserProfile(savedUser.getUserId(), savedUser.getUserName(), savedUser.getEmail(), savedUser.getRole(), savedUser.getWallet());
	}

	@Override
	public List<User> getAllUsers() 
	{	
		return userRepo.findAll();
	}

	@Override
	public UserProfile updateUserProfile(int userId, UserProfileUpdateRequest u) {
		
		// first checking is user present
		Optional<User> userOpt = userRepo.findById(userId);
		if(userOpt.isPresent())
		{
			// updating the user fields
			User existingUser = userOpt.get();
			existingUser.setUserName(u.getUserName());
			existingUser.setEmail(u.getEmail());
			existingUser.setRole(u.getRole());
			existingUser.setWallet(u.getWallet());
			existingUser.setUpdatedAt(LocalDate.now()); // updating the date
			// save the updates and return it
			User updatedUser = userRepo.save(existingUser);
			return new UserProfile(updatedUser.getUserId(), updatedUser.getUserName(), updatedUser.getEmail(), updatedUser.getRole(), updatedUser.getWallet());
		}
		throw new UserNotFoundException("User not found with id: " + userId);
	}

	@Override
	public UserProfile getUserById(int userId) {
		
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent())
		{
			User userEntity = user.get();
			System.out.println(userEntity.getWallet());
			return new UserProfile(userEntity.getUserId(), userEntity.getUserName(), userEntity.getEmail(), userEntity.getRole(), userEntity.getWallet());
		}
		throw new UserNotFoundException("User not found with id: " + userId);
	}

	@Override
	public boolean deleteUser(int userId) {
		 
		Optional<User> user = userRepo.findById(userId);
		
		if (user.isPresent()) {
            userRepo.delete(user.get());
            return true; // Deleted the user successfully and return true
        } 
		else 
		{
            throw new UserNotFoundException("User not found with id: " + userId);
		}
		
	}

}
