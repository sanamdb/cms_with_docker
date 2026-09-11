package com.learning.college.CollegeManagement.dto;

import org.springframework.http.HttpStatus;

public class ExceptionResponseDTO {
	
	private String message;
	private String description;
	private HttpStatus status;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}

