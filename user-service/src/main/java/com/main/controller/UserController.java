package com.main.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.UserProfile;
import com.main.dto.UserProfileUpdateRequest;
import com.main.dto.UserRegistrationRequest;
import com.main.entity.User;
import com.main.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/finguard")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	// adding new users
	@PostMapping("/user")
	public ResponseEntity<UserProfile> registerUsers(@RequestBody @Valid UserRegistrationRequest request)
	{
		UserProfile newUserprofile = userservice.addNewUsers(request);
		return new ResponseEntity<>(newUserprofile, HttpStatus.CREATED);
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
    	UserProfile userById = userservice.getUserById(userId);
        return new ResponseEntity<>(userById, HttpStatus.OK);
    }

    // Update a user by ID
    @PutMapping("/user/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable int userId, @RequestBody UserProfileUpdateRequest request) 
    {
    	UserProfile updatedUser = userservice.updateUserProfile(userId, request);
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
