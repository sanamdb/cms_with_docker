package com.learning.college.CollegeManagement.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.learning.college.CollegeManagement.entity.Department;
import com.learning.college.CollegeManagement.repository.DepartmentRepo;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
	
	@Mock
	private DepartmentRepo deptRepo;
	@InjectMocks
	private DepartmentService deptService;
	
	Department dept1;
	Department dept2;
	Department dept3;
	Department dept4;
	Department dept5;
	
	List<Department> codeName;
	List<Department> deptDuration;
	Page<Department> deptPage;
	
	@BeforeEach
	public void setUp() {
		dept1 = new Department();
		dept1.setId("101");
		dept1.setCode("Electronics");
		dept1.setName("ECE");
		dept1.setDescription("Bachelor of Technology in Electronics & Communication Engineering");
		dept1.setDuration(8);
		dept1.setTermType("semester");
		
		dept2 = new Department();
		dept2.setCode("Computer");
		dept2.setName("MCA");
		dept2.setDescription("Master of computer application");
		dept2.setDuration(4);
		dept2.setTermType("semester");
		
		dept3 = new Department();
		dept3.setCode("Computer");
		dept3.setName("BCA");
		dept3.setDescription("Bachelor of computer application");
		dept3.setDuration(6);
		dept3.setTermType("semester");
		
		dept4 = new Department();
		dept4.setCode("Computer");
		dept4.setName("CSE");
		dept4.setDescription("Bachelor of Technology in computer Science");
		dept4.setDuration(8);
		dept4.setTermType("semester");
		
		dept5 = new Department();
		dept5.setCode("Computer");
		dept5.setName("IT");
		dept5.setDescription("Bachelor of Technology in information technology");
		dept5.setDuration(8);
		dept5.setTermType("semester");
		
		codeName = new ArrayList<>();
		codeName.add(dept1);
		
		deptDuration = new ArrayList<>();
		deptDuration.add(dept2);
		deptDuration.add(dept3);
		deptDuration.add(dept4);
		deptDuration.add(dept5);
		
		deptPage = new PageImpl<Department>(deptDuration);	
	}
	
	@AfterEach
	public void tearDown() {
		deptRepo = null;
		codeName = null;
		deptDuration = null;
		deptPage = null;
		dept1 = null;
		dept2 = null;
		dept3 = null;
		dept4 = null;
		dept5 = null;
	}
	
	@Test
	public void saveDepartmentTest_Success() {
		when(deptRepo.save(dept1)).thenReturn(dept1);
		Department result = deptService.saveDepartment(dept1);
		Assertions.assertNotNull(result);
		Assertions.assertEquals("Electronics", result.getCode());
	}

	@Test
	public void findDepartmentByCodeAndNameTest_Success() {
		when(deptRepo.findByCodeAndName("Electronics", "ECE")).thenReturn(codeName);
		List<Department> result = deptService.findDepartmentByCodeAndName("Electronics", "ECE");
		Assertions.assertNotNull(result);
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(codeName.size(), result.size());
		Assertions.assertIterableEquals(codeName, result);
		Assertions.assertEquals(codeName.get(0).getName(), result.get(0).getName());
		
	}

	@Test
	public void findDepartmentByNameAndDurationTest_Success() {
		Pageable page = PageRequest.of(0, 4, Sort.by("name"));
		when(deptRepo.findByCodeAndDurationGreaterThan("Computer", 3, page)).thenReturn(deptPage);
		Page<Department> result = deptService.findDepartmentByNameAndDuration("Computer", 3, 0, 4);
		Assertions.assertNotNull(result);
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(4, result.getTotalElements());
	}

	@Test
	public void findDepartmentById() {
		when(deptRepo.findById("101")).thenReturn(Optional.of(dept1));
		Department result = deptService.findDepartmentById("101");
		Assertions.assertEquals("101", result.getId());
		Assertions.assertEquals(dept1.getName(), result.getName());
	}

}
