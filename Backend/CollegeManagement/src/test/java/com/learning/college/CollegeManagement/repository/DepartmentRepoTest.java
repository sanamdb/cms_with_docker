package com.learning.college.CollegeManagement.repository;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.learning.college.CollegeManagement.entity.Department;

@DataJpaTest
public class DepartmentRepoTest {

	@Autowired
	private DepartmentRepo deptRepo;
	
	Department dept1;
	Department dept2;
	Department dept3;
	Department dept4;
	Department dept5;
	
	@BeforeEach
	public void setUp() {
		dept1 = new Department();
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
		
		deptRepo.save(dept1);
		deptRepo.save(dept2);
		deptRepo.save(dept3);
		deptRepo.save(dept4);
		deptRepo.save(dept5);
	}
	
	@AfterEach
	public void tearDown() {
		deptRepo.deleteAll();
		deptRepo = null;
		dept1 = null;
		dept2 = null;
		dept3 = null;
		dept4 = null;
		dept5 = null;
	}
	
	@Test
	public void findByCodeAndNameTest_Success() {
		List<Department> result = deptRepo.findByCodeAndName("Electronics", "ECE");
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(1, result.size());
		Assertions.assertEquals(8, result.get(0).getDuration());
	}
	
	@Test
	public void findByCodeAndDurationGreaterThanTest_Success()  {
		Pageable page = PageRequest.of(0, 4);
		Page<Department> result = deptRepo.findByCodeAndDurationGreaterThan("Computer", 5, page);
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(3, result.getNumberOfElements());
		Assertions.assertEquals("BCA", result.getContent().get(0).getName());
	}
	
}

/*
private String id; 
private String code; 
private String name;  
private String description; 
private int duration; 
private String termType;

*/