  package com.learning.college.CollegeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.learning.college.CollegeManagement.entity.Department;
import com.learning.college.CollegeManagement.repository.DepartmentRepo;

@Service
public class DepartmentService {
	
	@Autowired
	private DepartmentRepo repo;

	public Department saveDepartment(Department dept) {
		return repo.save(dept);
	}

	public List<Department> findDepartmentByCodeAndName(String code, String name) {
		return repo.findByCodeAndName(code, name);
	}

	public Page<Department> findDepartmentByNameAndDuration(String code, int duration, int page, int size) {
		Pageable pageRequest = PageRequest.of(page, size, Sort.by("name"));
		return repo.findByCodeAndDurationGreaterThan(code, duration, pageRequest);	
	}

	public Department findDepartmentById(String departmentId) {
		return repo.findById(departmentId).orElse(null);
	}

	public List<Department> getAllDepartment() {
		return repo.findAll();
	}

}
