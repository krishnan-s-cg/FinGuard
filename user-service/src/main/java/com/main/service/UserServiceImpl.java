package com.main.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.client.NotificationClient;
import com.main.dto.AccountCreationEmailRequest;
import com.main.dto.UserProfile;
import com.main.dto.UserProfileUpdateRequest;
import com.main.dto.UserRegistrationRequest;
import com.main.entity.User;
import com.main.exception.UserNotFoundException;
import com.main.repository.UserRepository;


@Service 
public class UserServiceImpl implements UserService{
	
private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private static final String USER_NOT_FOUND_MSG = "User profile not found with id: ";
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private NotificationClient notificationClient;
	
	@Override
	public UserProfile addNewUsers(UserRegistrationRequest addUsers) 
	{
		logger.info("Adding a new user with username: {}", addUsers.getUserName());
		
		User user = new User();
		user.setUserName(addUsers.getUserName());
		user.setPassword(addUsers.getPassword());
        user.setEmail(addUsers.getEmail());
        user.setRole(addUsers.getRole());
        
        User savedUser = userRepo.save(user);
        
        logger.debug("{} saved successfully with id: {}", savedUser.getRole(),savedUser.getUserId());
        
        AccountCreationEmailRequest emailrequest = new AccountCreationEmailRequest(savedUser.getEmail(), "Account Created", "Account created, thank you for choosing us.");
        
        notificationClient.sendAccountCreationEmail(emailrequest);
        
        logger.info("Account Creation email sent to {}", savedUser.getEmail());
        
        return new UserProfile(savedUser.getUserId(), savedUser.getUserName(), savedUser.getEmail(), savedUser.getRole(), savedUser.getWallet());
	}

	@Override
	public List<User> getAllUsers() 
	{	
		logger.info("Fetching all users from the database.");
		return userRepo.findAll();
	}

	@Override
	public UserProfile updateUserProfile(int userId, UserProfileUpdateRequest u) {
		
		logger.info("Updating profile for user with id: {}", userId);
		
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
			logger.info("User profile updated successfully for userId: {}", userId);
			
			return new UserProfile(updatedUser.getUserId(), updatedUser.getUserName(), updatedUser.getEmail(), updatedUser.getRole(), updatedUser.getWallet());
		}
		else
		{
		logger.error("User with id {} not found for profile update", userId);
		throw new UserNotFoundException(USER_NOT_FOUND_MSG + userId);
		}
	}

	@Override
	public UserProfile getUserById(int userId) {
		
		logger.info("Fetching Profile by id: {}", userId);
		
		Optional<User> user = userRepo.findById(userId);
		if(user.isPresent())
		{
			User userEntity = user.get();
			
			logger.debug("Found user with Id: {} and wallet balance: {}", userEntity.getUserId(), userEntity.getWallet());
			
			return new UserProfile(userEntity.getUserId(), userEntity.getUserName(), userEntity.getEmail(), userEntity.getRole(), userEntity.getWallet());
		}
		else
		{
			logger.error("User with id {} not found", userId);
			throw new UserNotFoundException(USER_NOT_FOUND_MSG + userId);
		}
	}

	@Override
	public boolean deleteUser(int userId) {
		
		logger.info("Deleting user with id: {}", userId);
		 
		Optional<User> user = userRepo.findById(userId);
		
		if (user.isPresent()) {
            userRepo.delete(user.get());
            logger.info("User profile with Id: {} deleted successfully", userId);
            return true; // Deleted the user successfully and return true
        } 
		else 
		{
			logger.error("User profile with Id: {} not found for deletion", userId);
            throw new UserNotFoundException(USER_NOT_FOUND_MSG + userId);
		}
		
	}

}
