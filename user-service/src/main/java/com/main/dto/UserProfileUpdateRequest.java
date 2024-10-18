package com.main.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserProfileUpdateRequest {

	private String userName;
	private String email;
	private String role; // role can be updated by admin
}
