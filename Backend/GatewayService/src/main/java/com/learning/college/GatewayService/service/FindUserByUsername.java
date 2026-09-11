package com.learning.college.GatewayService.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learning.college.GatewayService.dto.Users;

@Service
public class FindUserByUsername {

	@Autowired
	private RestTemplate restTemplate;
	
	public Users findActiveUserForAuth(String username) {
		String url = "http://USER-MANAGEMENT-SYSTEM/users/user/{username}";
		Map<String, String> pathVariable = Map.of("username", username);
		
		HttpHeaders headers = new HttpHeaders();
		headers.set("accept", "application/json");
		headers.set("X-VERSION-API", "1");
		
		HttpEntity<Void> entity = new HttpEntity<>(headers);
		
		ResponseEntity<Users> response =
				restTemplate.exchange(url, HttpMethod.GET, entity, Users.class, pathVariable);
		
		return response.getBody();
	}
}
