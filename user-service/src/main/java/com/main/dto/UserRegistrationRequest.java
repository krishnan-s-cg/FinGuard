package com.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserRegistrationRequest 
{
	private String userName;
	private String password;
	private String email;
	private String role;
}
