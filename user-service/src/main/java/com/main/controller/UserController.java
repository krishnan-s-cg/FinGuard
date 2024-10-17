package com.main.controller;

import java.util.List;
import java.util.Optional;

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
import com.main.repository.UserRepository;
import com.main.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userservice;
	
	// adding new users
	@PostMapping
	public ResponseEntity<UserProfile> registerUsers(@RequestBody UserRegistrationRequest request)
	{
		UserProfile newUserprofile = userservice.addNewUsers(request);
		return new ResponseEntity<>(newUserprofile, HttpStatus.CREATED);
	}
	
	// Getting All Users
	@GetMapping
    public ResponseEntity<List<User>> getAllUsers() 
	{
        List<User> users = userservice.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK); 
    }

    // Get a user by ID
    @GetMapping("/{userId}")
    public ResponseEntity<UserProfile> getUserById(@PathVariable int userId) 
    {
        UserProfile userById = userservice.getUserById(userId);
        return new ResponseEntity<>(userById, HttpStatus.OK); 
    }

    // Update a user by ID
    @PutMapping("/{userId}")
    public ResponseEntity<UserProfile> updateUser(@PathVariable int userId, @RequestBody UserProfileUpdateRequest request) 
    {
        UserProfile updatedUser = userservice.updateUserProfile(userId, request);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK); 
    }

    // Delete a user by ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable int userId) 
    {
        userservice.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT); 
    }
}
