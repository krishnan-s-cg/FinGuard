package com.main.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.AuthRequest;
import com.main.dto.ResponseDto;

import com.main.dto.UpdateWallet;

import com.main.dto.UserProfileUpdateRequest;
import com.main.dto.UserRegistrationRequest;
import com.main.entity.User;
import com.main.repository.UserRepository;
import com.main.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/finguard")
public class UserController { 
	
	@Autowired
	private UserService userservice;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	// registering new users
	@PostMapping("/user/register")
	public ResponseEntity<User> registerUsers(@RequestBody @Valid UserRegistrationRequest request)
	{
		User newUser = userservice.addNewUsers(request);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	// Login for existing user
	@PostMapping("/user/login")
    public ResponseDto getToken(@RequestBody AuthRequest authRequest) 
	{
    	System.out.println("yes .."+authRequest.getUserName()+"  "+authRequest.getPassword());
    	
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
        
        System.out.println(authenticate.isAuthenticated());
        
        if (authenticate.isAuthenticated()) 
        {
        	User user= userRepo.findByUserName(authRequest.getUserName()).get();
        	String token=
        	//service.generateToken(authRequest.getUsername(),user.getRole());
        			userservice.generateToken(user.getUserId()+"",user.getRole());
        	
        	ResponseDto resDto=new ResponseDto();
        	resDto.setToken(token);
        	resDto.setRole(user.getRole());
        	return resDto;
        } else {
            throw new RuntimeException("invalid access");
        }
    }
	
	// Cheking the token is valid
	@GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        userservice.validateToken(token);
        return "Token is valid";
    }
	
	// Getting All Users
	@GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() 
	{
        List<User> users = userservice.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK); 
    }

    // Get a user by ID 
    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getUserById(@PathVariable int userId) 
    {
    	User userById = userservice.getUserById(userId);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    // Update a user by ID 
    @PutMapping("/user/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable int userId, @RequestBody UserProfileUpdateRequest request) 
    {
    	User updatedUser = userservice.updateUserProfile(userId, request);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK); // UserNotFoundException handled globally
    }
    
    // Update Users Wallet after he Logins
    @PutMapping("/user/wallet/{userId}")
    public ResponseEntity<Object> updateUserWallet(@PathVariable int userId, @RequestBody UpdateWallet walletUpdateRequest) 
    {
    	User updatedUser = userservice.updateUserWallet(userId, walletUpdateRequest.getAmount());
        return new ResponseEntity<>(updatedUser, HttpStatus.OK); // UserNotFoundException handled globally
    }

    // Delete a user by ID
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable int userId) 
    {
    	userservice.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); // UserNotFoundException handled globally
    }
}
