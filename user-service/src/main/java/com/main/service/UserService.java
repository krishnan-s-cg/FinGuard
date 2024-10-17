package com.main.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.main.dto.UserDTO;
import com.main.entity.User;
import com.main.repository.UserRepository;

@Service
public class UserService 
{
	@Autowired
	private UserRepository userRepo;
	
	public UserDTO getUserDTOById(int userId)
	{
		Optional<User> user = userRepo.findById(userId);
		
		if(user.isPresent())
		{
			User userEntity = user.get();
			return new UserDTO(userEntity.getUserId(), userEntity.getUserName(), userEntity.getEmail(), userEntity.getRole());
		}
		throw new RuntimeException("User not found with id: " + userId);
		
	}
}
