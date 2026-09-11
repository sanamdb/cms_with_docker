package com.learning.college.CollegeManagement.dto;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CampusRequestDTO {

	private String name;
	private String address;
	private String city;
	
}
