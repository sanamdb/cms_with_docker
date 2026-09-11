package com.learning.college.UsersManagementSystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.UsersManagementSystem.dto.UserResponseDTO;
import com.learning.college.UsersManagementSystem.dto.UserResponseInternalDTO;
import com.learning.college.UsersManagementSystem.entity.Users;
import com.learning.college.UsersManagementSystem.exception.UsernameNotFoundException;
import com.learning.college.UsersManagementSystem.service.UserService;

@RestController
@RequestMapping("users")
public class UserLoadController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "user/{username}", params = "version=1")
	public ResponseEntity<UserResponseDTO> findAllUser(@PathVariable("username") String username) throws UsernameNotFoundException {
		Users user = userService.findAllUser(username);
		if(user == null)
			throw new UsernameNotFoundException("Username not found", "Username "+username+ " is not register with us");
		
		UserResponseDTO response = new UserResponseDTO();
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());
		response.setPhoneNo(user.getPhoneNo());
		response.setStatus(user.getStatus());
		
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}
	
	@GetMapping(path = "user/{username}", headers =  "X-VERSION-API=1")
	public ResponseEntity<UserResponseInternalDTO> findUserForAuth(@PathVariable("username") String username) throws UsernameNotFoundException {
		Users user = userService.findActiveUser(username);
		if(user == null)
			throw new UsernameNotFoundException("Username not found", "Username "+username+ " is not register with us");
		
		UserResponseInternalDTO response = new UserResponseInternalDTO();
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());
		response.setPhoneNo(user.getPhoneNo());
		response.setStatus(user.getStatus());
		response.setRole(user.getRole());
		response.setPassword(user.getPassword());
		
		return new ResponseEntity<>(response, HttpStatus.FOUND);
	}

}
