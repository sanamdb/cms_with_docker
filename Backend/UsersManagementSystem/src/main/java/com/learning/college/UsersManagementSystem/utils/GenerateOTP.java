package com.learning.college.UsersManagementSystem.utils;

import java.security.SecureRandom;

public class GenerateOTP {

	public String generateOTP() {
		
		SecureRandom secureRandom = new SecureRandom();
	    int otp = 100000 + secureRandom.nextInt(900000);
	    return String.valueOf(otp);
	    
	}

}
