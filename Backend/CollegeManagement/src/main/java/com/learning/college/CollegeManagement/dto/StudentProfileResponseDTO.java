package com.learning.college.CollegeManagement.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileResponseDTO {
	
	private String firstName;
	private String lastName;
	private String dob;
	private String email;
	private String alternateEmail;
	private String phoneNo;
	private String alternatePhoneNo;
	private String fullAddress;
	private String city;
	private String pincode;
	private String state;
	private String profilePic;
	
}
