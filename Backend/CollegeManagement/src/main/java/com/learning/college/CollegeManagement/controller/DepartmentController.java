package com.learning.college.CollegeManagement.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.CollegeManagement.dto.DepartmentRequestDTO;
import com.learning.college.CollegeManagement.dto.DepartmentResponseDTO;
import com.learning.college.CollegeManagement.entity.Department;
import com.learning.college.CollegeManagement.exception.DepartmentNotFoundException;
import com.learning.college.CollegeManagement.service.DepartmentService;
import com.learning.college.CollegeManagement.util.DepartmentUtils;

@RestController
@RequestMapping("college/api/v1")
public class DepartmentController {
	
	@Autowired
	private DepartmentService service;
	
	@PostMapping("department")
	public ResponseEntity<DepartmentResponseDTO> createDepartment(@RequestBody DepartmentRequestDTO department) throws DepartmentNotFoundException {
		
		Department dept = new Department();
		//dept.setId(new DepartmentUtils().getDepartmentId(department));
		dept.setCode(department.getCode());
		dept.setName(department.getName());
		dept.setDescription(department.getDescription());
		dept.setDuration(department.getDuration());
		dept.setTermType(department.getTerm_type());
		
		Department res = service.saveDepartment(dept);
		
		if(res == null)
			throw new DepartmentNotFoundException("Department creation failed", "Department couldn't be created right now, please try after some time");
		
		DepartmentResponseDTO response = new DepartmentResponseDTO();
		response.setId(res.getId());
		response.setCode(res.getCode());
		response.setName(res.getName());
		response.setDescription(res.getDescription());
		response.setDuration(res.getDuration());
		response.setTermType(res.getTermType());
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
		
	}
	
	@GetMapping("department/{id}")
	public ResponseEntity<DepartmentResponseDTO> findDepartmentById(@PathVariable("id") String id) throws DepartmentNotFoundException {
		Department response = service.findDepartmentById(id);
		if(response == null)
			throw new DepartmentNotFoundException("Department not found", "Department not found with id "+id);
		
		DepartmentResponseDTO responseDto = new DepartmentResponseDTO();
		responseDto.setId(response.getId());
		responseDto.setCode(response.getCode());
		responseDto.setDescription(response.getDescription());
		responseDto.setDuration(response.getDuration());
		responseDto.setName(response.getName());
		responseDto.setTermType(response.getTermType());
		
		return new ResponseEntity<>(responseDto, HttpStatus.OK);
	}
	
	@GetMapping("department")
	public ResponseEntity<List<DepartmentResponseDTO>> getAllDepartment() {
		List<Department> responses = service.getAllDepartment();
		List<DepartmentResponseDTO> responseList = new ArrayList<>();
		
		for(Department response : responses) {
			DepartmentResponseDTO dept = new DepartmentResponseDTO();
			dept.setId(response.getId());
			dept.setCode(response.getCode());
			dept.setName(response.getName());
			dept.setDescription(response.getDescription());
			dept.setDuration(response.getDuration());
			dept.setTermType(response.getTermType());
			responseList.add(dept);
		}
		
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@GetMapping("department/code/name")
	public ResponseEntity<List<DepartmentResponseDTO>> findDepartmentByCodeAndName(@RequestParam("code") String code, @RequestParam("name") String name) throws DepartmentNotFoundException {
		
		List<Department> responses = service.findDepartmentByCodeAndName(code, name);
		
		if(responses == null) 
			throw new DepartmentNotFoundException("Department not available", "Department not available with code "+code + " and name "+name);
		
		if(responses.isEmpty()) 
			throw new DepartmentNotFoundException("Department not available", "Department not available with code "+code + " and name "+name);
		
		List<DepartmentResponseDTO> responseList = new ArrayList<>();
		
		for(Department response : responses) {
			DepartmentResponseDTO dept = new DepartmentResponseDTO();
			dept.setId(response.getId());
			dept.setCode(response.getCode());
			dept.setName(response.getName());
			dept.setDescription(response.getDescription());
			dept.setDuration(response.getDuration());
			dept.setTermType(response.getTermType());
			responseList.add(dept);
		}
		
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	
	@GetMapping("department/code/duration")
	public ResponseEntity<Page<DepartmentResponseDTO>> findDepartmentByNameAndDuration(@RequestParam("code") String code, @RequestParam("duration") int duration, @RequestParam("page") int page, @RequestParam("size") int size) throws DepartmentNotFoundException {
		
		Page<Department> responses = service.findDepartmentByNameAndDuration(code, duration, page, size);
		
		if(responses == null) 
			throw new DepartmentNotFoundException("Department not available", "Department not available with code "+code + " and duration "+duration);
		
		if(responses.getTotalElements() == 0) 
			throw new DepartmentNotFoundException("Department not available", "Department not available with code "+code + " and duration "+duration);
		
		Page<DepartmentResponseDTO> responseList = responses.map(response -> {
			DepartmentResponseDTO dept = new DepartmentResponseDTO();
			dept.setId(response.getId());
			dept.setCode(response.getCode());
			dept.setName(response.getName());
			dept.setDescription(response.getDescription());
			dept.setDuration(response.getDuration());
			dept.setTermType(response.getTermType());
			return dept;	
		});
		
		return new ResponseEntity<>(responseList, HttpStatus.OK);
	}
	

}
