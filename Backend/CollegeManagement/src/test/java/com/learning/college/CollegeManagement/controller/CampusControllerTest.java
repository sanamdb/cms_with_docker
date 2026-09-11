package com.learning.college.CollegeManagement.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.learning.college.CollegeManagement.dto.CampusResponseDTO;
import com.learning.college.CollegeManagement.entity.Campus;
import com.learning.college.CollegeManagement.exception.CampusNotFoundException;
import com.learning.college.CollegeManagement.exception.GenericCollegeException;
import com.learning.college.CollegeManagement.service.CampusService;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(CampusController.class)
public class CampusControllerTest {
	
	@Autowired
	private MockMvc mvc;
	
	@MockitoBean
	private CampusService campService;
	
	Campus camp1;
	Campus camp2;
	Campus camp3;
	List<Campus> campCity;
	
	@BeforeEach
	public void setUp() {
		camp1 = new Campus();
		camp1.setId("101");
		camp1.setName("PES");
		camp1.setCity("Bangalore");
		camp1.setAddress("Ring Road");
		camp1.setEstablishedDate(LocalDate.now());
		
		camp2 = new Campus();
		camp2.setId("102");
		camp2.setName("Dayanand Sagar");
		camp2.setCity("Bangalore");
		camp2.setAddress("Kumara Swamy Layout");
		camp2.setEstablishedDate(LocalDate.now());
		
		camp3 = new Campus();
		camp3.setId("103");
		camp3.setName("Thakur College");
		camp3.setCity("Mumbai");
		camp3.setAddress("Navi mumbai");
		camp3.setEstablishedDate(LocalDate.now());
		
		campCity = new ArrayList<Campus>();
		campCity.add(camp1);
		campCity.add(camp2);
	}
	
	@AfterEach
	public void tearDown()  {
		camp1 = null;
		camp2 = null;
		camp3 = null;
		campCity = null;
	}
	
	@Test
	public void findCampusByCityTest_Success() throws Exception {
		when(campService.findCampusByCity("Bangalore")).thenReturn(campCity);
		this.mvc.perform(get("/college/api/v1/campus/city")
				.param("city", "Bangalore")
			).andExpect(status().isOk())
				.andExpect(jsonPath("$.size()").value(2))
				.andExpect(jsonPath("$[0].name").value("PES"));
	}
	
	@Test
	public void deleteCampusTest_Success() throws Exception {
		when(campService.findCampusById("103")).thenReturn(camp3);
		doNothing().when(campService).deleteCampusById("103");
		this.mvc.perform(delete("/college/api/v1/campus/103"))
			.andExpect(status().isOk());
	}

}