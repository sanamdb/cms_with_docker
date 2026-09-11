package com.learning.college.UsersManagementSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.college.UsersManagementSystem.entity.Users;

public interface UsersRepo extends JpaRepository<Users, String>{

	Users findByUsername(String username);
	
	Users findByUsernameAndStatusEquals(String username, String status);

}
