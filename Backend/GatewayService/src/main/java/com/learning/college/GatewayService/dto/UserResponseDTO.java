package com.learning.college.GatewayService.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {
	
	private String username;
	private String token;
	private String status;
	private String role;
	private LocalDateTime expireAt; 
	
}
