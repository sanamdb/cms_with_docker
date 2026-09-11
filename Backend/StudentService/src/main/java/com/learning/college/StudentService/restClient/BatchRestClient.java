package com.learning.college.StudentService.restClient;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learning.college.StudentService.dto.BatchResponseDTO;
import com.learning.college.StudentService.dto.DepartmentResponseDTO;

@Service
public class BatchRestClient {

	@Autowired
	private RestTemplate restTemplate;
	
	public BatchResponseDTO getBatchByYear(String year) {
		
		String url = "http://COLLEGE-MANAGEMENT/college/api/v1/batch/year?year={year}";
		return restTemplate.getForObject(url, BatchResponseDTO.class, year);
		
	}
}
