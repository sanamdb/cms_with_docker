package com.learning.college.UsersManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
	
	private String username;
	private String email;
	private String phoneNo;
	private String status;
	
}
