package com.learning.college.GatewayService.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthErrorResponseDTO {
	
    private int status;
    private String error;
    private String message;
    private String path;
    
}
