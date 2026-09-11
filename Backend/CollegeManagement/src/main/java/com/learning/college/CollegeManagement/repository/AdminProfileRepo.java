package com.learning.college.CollegeManagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.college.CollegeManagement.entity.AdminProfile;

@Repository
public interface AdminProfileRepo extends JpaRepository<AdminProfile, String> {
	AdminProfile findByUsername(String username);
}
