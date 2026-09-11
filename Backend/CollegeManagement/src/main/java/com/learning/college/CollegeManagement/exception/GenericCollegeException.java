package com.learning.college.CollegeManagement.exception;

public class GenericCollegeException extends Exception {
	
	private String description;
	
	public GenericCollegeException(String message, String description) {
		super(message);
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
