package com.learning.college.UsersManagementSystem.controller;

import java.util.concurrent.CompletableFuture;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.UsersManagementSystem.dto.OtpRequestDto;
import com.learning.college.UsersManagementSystem.dto.UserOtpValidateResponse;
import com.learning.college.UsersManagementSystem.dto.UserRequestDTO;
import com.learning.college.UsersManagementSystem.dto.UserResponseDTO;
import com.learning.college.UsersManagementSystem.entity.Otp;
import com.learning.college.UsersManagementSystem.entity.Users;
import com.learning.college.UsersManagementSystem.exception.OtpValidationException;
import com.learning.college.UsersManagementSystem.exception.UserGenericException;
import com.learning.college.UsersManagementSystem.exception.UsernameNotFoundException;
import com.learning.college.UsersManagementSystem.service.UserService;

@RestController
@RequestMapping("users/api/v1")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("user")
	public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO user) throws OtpValidationException, UserGenericException, UsernameNotFoundException {
		
		Users available = userService.checkAvailibility(user.getUsername());
		if(available != null)
			throw new UsernameNotFoundException("Username not available", "Username "+user.getUsername() +" not available, Kindly choose different username");
		
		CompletableFuture<Otp> otp = userService.sendOtp(user);
		if(otp == null)
			throw new OtpValidationException("OTP Failed", "Otp couldn't be send");
		
		Users users = userService.registration(user);
		if(users == null)
			throw new UserGenericException("Registration Failed", "User can not be register right now");
		
		UserResponseDTO response = new UserResponseDTO();
		response.setEmail(users.getEmail());
		response.setPhoneNo(users.getPhoneNo());
		response.setStatus(users.getStatus());
		response.setUsername(users.getUsername());
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@PostMapping("otp/validate")
	public ResponseEntity<UserOtpValidateResponse> validateOtp(@RequestBody OtpRequestDto otp) throws UsernameNotFoundException, OtpValidationException {
		
		UserOtpValidateResponse response = userService.validateOtp(otp);
		
		if(response==null)
			throw new UsernameNotFoundException("User not found", otp.getUsername() + " is not registered with us");
		
		if(response.getStatus().equalsIgnoreCase("UNVERIFIED")) 
			throw new OtpValidationException(response.getStatus(), response.getMessage());
		
		return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
			
	}
	
	@GetMapping("user/active/{username}")
	public ResponseEntity<UserResponseDTO> findActiveUser(@PathVariable("username") String username) throws UsernameNotFoundException {
		
		Users user = userService.findActiveUser(username);
		if(user == null)
			throw new UsernameNotFoundException("Username not found", "Username "+username + " not registered with us");
		
		UserResponseDTO response = new UserResponseDTO();
		response.setUsername(user.getUsername());
		response.setEmail(user.getEmail());
		response.setPhoneNo(user.getPhoneNo());
		response.setStatus(user.getStatus());
		
		return new ResponseEntity<>(response, HttpStatus.FOUND);
		
	}
	
	
}
