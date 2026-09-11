package com.learning.college.UsersManagementSystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
	
	@Id
	private String id;
	private String username;
	private String password;
	private String email;
	private String phoneNo;
	private String status;
	private String role;
	private LocalDateTime registerAt;
	
}
