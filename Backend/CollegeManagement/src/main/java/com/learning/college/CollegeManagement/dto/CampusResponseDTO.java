package com.learning.college.CollegeManagement.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampusResponseDTO {
	
	private String id;
	private String name;
	private String address;
	private String city;
	private LocalDate establishedDate;
	private String campusPicture;
	
}
