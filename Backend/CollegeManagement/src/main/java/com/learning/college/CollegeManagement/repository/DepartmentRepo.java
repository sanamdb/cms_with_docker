package com.learning.college.CollegeManagement.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.learning.college.CollegeManagement.entity.Department;

public interface DepartmentRepo extends JpaRepository<Department, String>{

	List<Department> findByCodeAndName(String code, String name);
	
	Page<Department> findByCodeAndDurationGreaterThan(String code, int duration, Pageable pageRequest);
}
