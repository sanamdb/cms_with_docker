package com.learning.college.NotificationService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
	
	private String email;
	private String message;
	private String subject;
	private String cc;

}
