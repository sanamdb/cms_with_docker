package com.learning.college.UsersManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseInternalDTO {

	private String username;
	private String email;
	private String phoneNo;
	private String status;
	private String password;
	private String role;
	
}
