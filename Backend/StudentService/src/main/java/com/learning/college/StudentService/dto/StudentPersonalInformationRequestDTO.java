package com.learning.college.StudentService.dto;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentPersonalInformationRequestDTO {

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
	
}
