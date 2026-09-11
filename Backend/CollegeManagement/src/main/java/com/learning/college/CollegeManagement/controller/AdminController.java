package com.learning.college.CollegeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.CollegeManagement.dto.AdminProfileDto;
import com.learning.college.CollegeManagement.entity.AdminProfile;
import com.learning.college.CollegeManagement.service.AdminService;

import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("college/api/v1")
public class AdminController {
	
	@Autowired
	private AdminService adminService;
	
	@GetMapping("admin/profile/{username}")
	public ResponseEntity<AdminProfileDto> getAdminProfile(@PathVariable("username") String username) {
		AdminProfile adminProfile = adminService.getAdminProfile(username);
		AdminProfileDto response = new AdminProfileDto();
		
		response.setFirstName(adminProfile.getFirstName());
		response.setLastName(adminProfile.getLastName());
		response.setDob(adminProfile.getDob());
		response.setEmail(adminProfile.getEmail());
		response.setAlternateEmail(adminProfile.getAlternateEmail());
		response.setPhoneNo(adminProfile.getPhoneNo());
		response.setAlternatePhoneNo(adminProfile.getAlternatePhoneNo());
		response.setFullAddress(adminProfile.getFullAddress());
		response.setCity(adminProfile.getCity());
		response.setPincode(adminProfile.getPincode());
		response.setState(adminProfile.getState());
		response.setProfilePic(adminProfile.getProfilePic());
		response.setUsername(adminProfile.getUsername());
		
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
