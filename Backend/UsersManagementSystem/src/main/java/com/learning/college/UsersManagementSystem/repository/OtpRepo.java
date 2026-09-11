package com.learning.college.UsersManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.college.UsersManagementSystem.entity.Otp;

public interface OtpRepo extends JpaRepository<Otp, String>{

	Otp findByUsername(String username);
}
