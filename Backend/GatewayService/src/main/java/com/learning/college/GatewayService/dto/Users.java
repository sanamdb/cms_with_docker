package com.learning.college.GatewayService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	private String username;
	private String email;
	private String phoneNo;
	private String status;
	private String password;
	private String role;
	
}

