package com.learning.college.CollegeManagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminProfileDto {
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
    private String profilePic;
    private String username;
}