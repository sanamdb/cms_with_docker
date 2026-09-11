package com.learning.college.StudentService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Student_Personal_Information")
public class Student {
	
	@Id
	private String id;
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
	@Lob
	@Column(name = "profile_pic", columnDefinition = "CLOB")
	private String profilePic;
	
}
