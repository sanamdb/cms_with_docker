package com.learning.college.CollegeManagement.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learning.college.CollegeManagement.dto.StudentProfileResponseDTO;

@Service
public class StudentProfileRest {
	
	@Autowired
	private RestTemplate restTemplate;
	
	public StudentProfileResponseDTO getStudentProfile(String userId) {
		String url = "http://STUDENT-SERVICE/student/api/v1/student/{userId}";
		return restTemplate.getForObject(url, StudentProfileResponseDTO.class, userId);
	}

}
