package com.learning.college.CollegeManagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.college.CollegeManagement.entity.AdminProfile;
import com.learning.college.CollegeManagement.repository.AdminProfileRepo;

@Service
public class AdminService {
	
	@Autowired
	private AdminProfileRepo adminRepo;

	public AdminProfile getAdminProfile(String username) {
		return adminRepo.findByUsername(username);
	}

}
