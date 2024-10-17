package com.main.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.main.dto.UserDTO;
import com.main.entity.User;
import com.main.repository.UserRepository;
import com.main.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userservice;

	@GetMapping("/users/dto/{userId}")
	public ResponseEntity<UserDTO> getUserDTO(@PathVariable int userId)
	{
		UserDTO userDTO = userservice.getUserDTOById(userId);
		return ResponseEntity.ok(userDTO);
	}
}
