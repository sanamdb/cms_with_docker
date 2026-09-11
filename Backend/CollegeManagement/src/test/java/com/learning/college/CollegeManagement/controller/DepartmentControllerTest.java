package com.learning.college.CollegeManagement.controller;

import static org.mockito.Mockito.when;

import java.awt.print.Printable;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.learning.college.CollegeManagement.dto.DepartmentRequestDTO;
import com.learning.college.CollegeManagement.dto.DepartmentResponseDTO;
import com.learning.college.CollegeManagement.entity.Department;
import com.learning.college.CollegeManagement.exception.DepartmentNotFoundException;
import com.learning.college.CollegeManagement.service.DepartmentService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.http.MediaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mockito.ArgumentMatchers;


@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockitoBean
	private DepartmentService deptService;
	
	Department dept1;
	Department dept2;
	
	private ObjectMapper objectMapper = new ObjectMapper();
	
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
		dept2.setId("102");
		dept2.setCode("Computer");
		dept2.setName("MCA");
		dept2.setDescription("Master of computer application");
		dept2.setDuration(4);
		dept2.setTermType("semester");
	}
	
	@AfterEach
	public void tearDown() {
		dept1 = null;
		dept2 = null;
	}
	
	@Test
	public void createDepartmentTest_Success() throws Exception {
		String jsonRequest = objectMapper.writeValueAsString(dept1);
		when(deptService.saveDepartment(ArgumentMatchers.any(Department.class))).thenReturn(dept1);
		this.mvc.perform(post("/college/api/v1/department")
					.contentType(MediaType.APPLICATION_JSON)
					.content(jsonRequest)
				).andExpect(status().isCreated())
					.andExpect(jsonPath("$.size()").value(6))
					.andExpect(jsonPath("$.code").value("Electronics"));
	}
	
	@Test
	public void findDepartmentByIdTest_Success() throws Exception {
		when(deptService.findDepartmentById("102")).thenReturn(dept2);
		this.mvc.perform(get("/college/api/v1/department/102"))
		         .andExpect(status().isOk())
		         .andExpect(jsonPath("$.code").value("Computer"));
	}

}
