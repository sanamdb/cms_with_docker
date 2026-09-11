package com.learning.college.CollegeManagement.health;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.learning.college.CollegeManagement.dto.ExceptionResponseDTO;
import com.learning.college.CollegeManagement.dto.HealthResponse;
import com.learning.college.CollegeManagement.exception.ProfileNotSupportedException;

@Controller
@Profile("prod")
public class ProdHealth {
	
	@Value("${activeProfile}")
	private String profile;

	@RequestMapping("health")
	@ResponseBody
	public ResponseEntity<HealthResponse> health() throws ProfileNotSupportedException {
		
			if(profile.split(" ")[0].equalsIgnoreCase("prod"))
				throw new ProfileNotSupportedException("Prod profile is not supported in development stage");
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
