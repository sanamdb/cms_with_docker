package com.learning.college.UsersManagementSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOtpValidateResponse {
	
	private String username;
	private String status;
	private String message;

}
