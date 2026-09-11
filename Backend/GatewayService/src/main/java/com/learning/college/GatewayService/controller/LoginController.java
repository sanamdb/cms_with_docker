package com.learning.college.GatewayService.controller;


import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.GatewayService.dto.UserRequestDTO;
import com.learning.college.GatewayService.dto.UserResponseDTO;
import com.learning.college.GatewayService.dto.Users;
import com.learning.college.GatewayService.service.FindUserByUsername;
import com.learning.college.GatewayService.service.TokenService;

@RestController
@RequestMapping("auth/api/v1")
public class LoginController {
	
	@Autowired
	private FindUserByUsername restService;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;

	@PostMapping("login")
	public ResponseEntity<UserResponseDTO> login(@RequestBody UserRequestDTO user) {
		
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		Users userRes = restService.findActiveUserForAuth(user.getUsername());
		UserResponseDTO response = new UserResponseDTO();
		int minutes = 50;
		if(authentication.isAuthenticated()) {
			String token = tokenService.generateToken(user, minutes);
			response.setUsername(user.getUsername());
			response.setStatus(userRes.getStatus());
			response.setRole(userRes.getRole().split("_")[1]);
			response.setExpireAt(LocalDateTime.now().plusMinutes(minutes));
			response.setToken(token);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
