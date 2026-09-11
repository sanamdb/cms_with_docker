package com.learning.college.CollegeManagement.dto;

import org.springframework.http.HttpStatus;

public class ProfileResponseDTO {
	
	private String profile;
	private HttpStatus status;
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}

}
