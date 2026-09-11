package com.learning.college.CollegeManagement.util;

import com.learning.college.CollegeManagement.dto.DepartmentRequestDTO;

public class DepartmentUtils {

	public String getDepartmentId(DepartmentRequestDTO department) {
		return department.getCode() + "-" + department.getName();
	}
	
	

}
