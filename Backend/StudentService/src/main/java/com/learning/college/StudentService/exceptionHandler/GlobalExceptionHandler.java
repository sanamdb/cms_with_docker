package com.learning.college.StudentService.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.learning.college.StudentService.dto.ErrorResponse;
import com.learning.college.StudentService.exception.ApplyFormSubmissionException;
import com.learning.college.StudentService.exception.StudentGenericException;
import com.learning.college.StudentService.exception.UsernameNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(com.learning.college.StudentService.exception.UsernameNotFoundException.class)
	public ResponseEntity<ErrorResponse> usernameNotFound(UsernameNotFoundException e) {
		
		ErrorResponse res = new ErrorResponse();
		res.setMessage(e.getMessage());
		res.setDescription(e.getDescription());
		
		return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(com.learning.college.StudentService.exception.StudentGenericException.class)
	public ResponseEntity<ErrorResponse> studentGenericException(StudentGenericException e) {
		
		ErrorResponse res = new ErrorResponse();
		res.setMessage(e.getMessage());
		res.setDescription(e.getDescription());
		
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(com.learning.college.StudentService.exception.ApplyFormSubmissionException.class)
	public ResponseEntity<ErrorResponse> applyFormSubmissionException(ApplyFormSubmissionException e) {
		
		ErrorResponse res = new ErrorResponse();
		res.setMessage(e.getMessage());
		res.setDescription(e.getDescription());
		
		return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
