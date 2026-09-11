package com.learning.college.StudentService.restClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learning.college.StudentService.dto.DepartmentResponseDTO;

@Service
public class DepartmentRestClient {

	@Autowired
	private RestTemplate restTemplate;
	
	public DepartmentResponseDTO getDepartmentById(String id) {
		
		String url = "http://COLLEGE-MANAGEMENT/college/api/v1/department/{id}";
		return restTemplate.getForObject(url, DepartmentResponseDTO.class, id);
		
	}
}
