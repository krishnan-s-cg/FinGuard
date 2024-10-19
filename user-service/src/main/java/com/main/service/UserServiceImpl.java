package com.main.service;

import java.math.BigDecimal;
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
import com.main.dto.WalletUpdateEmailRequest;
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
        
        AccountCreationEmailRequest emailrequest = new AccountCreationEmailRequest(savedUser.getEmail()
        		, "Welcome to FinGuard – Your Financial Journey Starts Here!"
        		, "Dear " + savedUser.getUserName() + ",\n"+ "We’re excited to welcome you to FinGuard! Thank you for choosing us to help manage your financial goals and journey. Your account has been successfully created, and you’re now part of a community dedicated to making finance management smarter and easier.\r\n"
        			+ "\r\n" + "Feel free to explore our features, from budget tracking to personalized financial insights. We’re here to support you every step of the way.\r\n"
        			+ "\r\n" + "If you have any questions or need assistance, our support team is always ready to help.\r\n"
        			+ "\r\n" + "Best regards,\r\n" + "The FinGuard Team");
        
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
		User existingUser = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MSG + userId));
		
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

	@Override
	public UserProfile getUserById(int userId) {
		
		logger.info("Fetching Profile by id: {}", userId);
		
		User userEntity = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MSG + userId));
			
			logger.debug("Found user with Id: {} and wallet balance: {}", userEntity.getUserId(), userEntity.getWallet());
			
			return new UserProfile(userEntity.getUserId(), userEntity.getUserName(), userEntity.getEmail(), userEntity.getRole(), userEntity.getWallet());
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

	@Override
	public UserProfile updateUserWallet(int userId, BigDecimal amount) 
	{
		
		logger.info("Updating Wallet for user with id: {}", userId);
		
		User existingUser = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND_MSG + userId));
		
		logger.debug("Current wallet balance for userId {}: {}",userId, existingUser.getWallet());
		
		BigDecimal newWalletAmount = existingUser.getWallet().add(amount);
		
		if(newWalletAmount.compareTo(BigDecimal.ZERO) < 0)
		{
			logger.error("Attempted to set negative wallet balance for UserId: {}. \nCurrent Balance: {}, attempted amount: {}", userId, existingUser.getWallet(), amount);
			
			throw new IllegalArgumentException("Cannot set wallet to a negative amount");
		}
		
		existingUser.setWallet(newWalletAmount);
		existingUser.setUpdatedAt(LocalDate.now());
		
		// saving the updated wallet
		User updatedUser= userRepo.save(existingUser);
		logger.info("Wallet updated successfully for userId: {}", userId);
		
		// Create the email request here
	    WalletUpdateEmailRequest emailRequest = new WalletUpdateEmailRequest(
	        updatedUser.getEmail(),
	        "Wallet Successfully Updated!",
	        "Dear " + updatedUser.getUserName() + ",\r\n" +
	        		"\r\n" +
	        		"We're excited to inform you that your wallet has been successfully credited with " + amount + ". Your new balance is now " + newWalletAmount + ".\r\n" +
	        		"\r\n" +
	        		"Thank you for trusting FinGuard to manage your finances. We’re here to support you every step of the way!\r\n" +
	        		"\r\n" +
	        		"If you have any questions or need assistance, feel free to contact our support team.\r\n" +
	        		"\r\n" +
	        		"Best regards,\r\n" +
	        		"The FinGuard Team");
	    
	    // Logging the email details before sending
	    logger.info("Sending wallet update email to: {}, subject: {}, body: {}", 
	        emailRequest.getToEmail(), 
	        emailRequest.getSubject(), 
	        emailRequest.getMessage());
	    
	    // Check if the email body is null or empty
	    if (emailRequest.getMessage() == null || emailRequest.getMessage().isEmpty()) {
	        logger.error("Email body is empty or null!");
	        throw new IllegalArgumentException("Email body cannot be empty.");
	    }

	    // Send the email
	    notificationClient.sendWalletUpdateEmail(emailRequest);
	    logger.info("Wallet update Email sent to {}", updatedUser.getEmail());
		
		return new UserProfile(updatedUser.getUserId(), updatedUser.getUserName(), updatedUser.getEmail(), updatedUser.getRole(), updatedUser.getWallet());

	}

}
