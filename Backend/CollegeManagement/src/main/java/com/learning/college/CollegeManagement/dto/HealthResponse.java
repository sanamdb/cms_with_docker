package com.learning.college.CollegeManagement.dto;

import org.springframework.http.HttpStatus;


public class HealthResponse {
	
	private String profile;
	private String health;
	private HttpStatus status;
	
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public String getHealth() {
		return health;
	}
	public void setHealth(String health) {
		this.health = health;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
}
