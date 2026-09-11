package com.learning.college.UsersManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

	private String username;
	private String password;
	private String email;
	private String phoneNo;
	
}
