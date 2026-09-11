package com.learning.college.UsersManagementSystem.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learning.college.UsersManagementSystem.dto.ErrorResponse;
import com.learning.college.UsersManagementSystem.exception.OtpValidationException;
import com.learning.college.UsersManagementSystem.exception.UserGenericException;
import com.learning.college.UsersManagementSystem.exception.UsernameNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(com.learning.college.UsersManagementSystem.exception.UsernameNotFoundException.class)
	public ResponseEntity<ErrorResponse> usernameNotFound(UsernameNotFoundException e) {
		
		ErrorResponse res = new ErrorResponse();
		res.setMessage(e.getMessage());
		res.setDescription(e.getDescription());
		
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}

	
	@ExceptionHandler(com.learning.college.UsersManagementSystem.exception.UserGenericException.class)
	public ResponseEntity<ErrorResponse> usernameGenericException(UserGenericException e) {
		
		ErrorResponse res = new ErrorResponse();
		res.setMessage(e.getMessage());
		res.setDescription(e.getDescription());
		
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(com.learning.college.UsersManagementSystem.exception.OtpValidationException.class)
	public ResponseEntity<ErrorResponse> otpValidationException(OtpValidationException e) {
		
		ErrorResponse res = new ErrorResponse();
		res.setMessage(e.getMessage());
		res.setDescription(e.getDescription());
		
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}
}
