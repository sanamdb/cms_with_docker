package com.learning.college.CollegeManagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Entity
@Table(name = "admin_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminProfile {

	@Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String firstName;
    private String lastName;
    private LocalDate dob;
    private String email;
    private String alternateEmail;
    private String phoneNo;
    private String alternatePhoneNo;
    private String fullAddress;
    private String city;
    private String pincode;
    private String state;
    private String username;
    @Lob
	@Column(name = "profile_pic", columnDefinition = "CLOB")
    private String profilePic;
    
}
