package com.learning.college.CollegeManagement.profile;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.learning.college.CollegeManagement.dto.ExceptionResponseDTO;
import com.learning.college.CollegeManagement.dto.ProfileResponseDTO;
import com.learning.college.CollegeManagement.exception.ProfileNotSupportedException;

@Controller
public class ActiveProfileController {
	
	@Value("${activeProfile}")
	private String profile;
	
	@RequestMapping("profile")
	public ResponseEntity<?> profile() {
		try {
			if(profile.split(" ")[0].equalsIgnoreCase("prod"))
				throw new ProfileNotSupportedException("Prod profile is not supported in development stage");
			ProfileResponseDTO res = new ProfileResponseDTO();
			res.setProfile(profile);
			res.setStatus(HttpStatus.OK);
			return new ResponseEntity<>(res, HttpStatus.OK);
		} catch(ProfileNotSupportedException e) {
			ExceptionResponseDTO res = new ExceptionResponseDTO();
			res.setMessage(e.getMessage());
			res.setDescription("Fetch Active Profile");
			res.setStatus(HttpStatus.BAD_REQUEST);
			return new ResponseEntity<>(res, HttpStatus.OK);
		}
		
	}

}