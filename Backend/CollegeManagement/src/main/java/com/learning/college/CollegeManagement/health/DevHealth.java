package com.learning.college.CollegeManagement.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.CollegeManagement.dto.ExceptionResponseDTO;
import com.learning.college.CollegeManagement.dto.HealthResponse;
import com.learning.college.CollegeManagement.exception.ProfileNotSupportedException;

@RestController
@Profile("dev")
public class DevHealth {
	
	@Value("${activeProfile}")
	private String profile;
	
	@RequestMapping("health") 
	public ResponseEntity<HealthResponse> health() {
		HealthResponse res = new HealthResponse();
		res.setProfile(profile);
		res.setHealth("RUNNING");
		res.setStatus(HttpStatus.OK);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	
	@ExceptionHandler(com.learning.college.CollegeManagement.exception.ProfileNotSupportedException.class)
	public ResponseEntity<ExceptionResponseDTO> profileNotSupported(ProfileNotSupportedException e) {
		ExceptionResponseDTO res = new ExceptionResponseDTO();
		res.setMessage(e.getMessage());
		res.setDescription("Profile health fetch");
		res.setStatus(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}


