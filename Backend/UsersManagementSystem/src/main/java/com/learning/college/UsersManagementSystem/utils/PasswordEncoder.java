package com.learning.college.UsersManagementSystem.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {

	public String encode(String password) {
		
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);
		String hash = bcrypt.encode(password);
		return hash;
	}

}
