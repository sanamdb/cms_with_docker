package com.learning.college.CollegeManagement.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.learning.college.CollegeManagement.entity.Campus;
import com.learning.college.CollegeManagement.repository.CampusRepo;

@ExtendWith(MockitoExtension.class)
public class CampusServiceTest {
	
	@Mock
	private CampusRepo campRepo;
	@InjectMocks
	private CampusService campService;
	
	Campus camp1;
	Campus camp2;
	Campus camp3;
	List<Campus> campCity;
	
	@BeforeEach
	public void setUp() {
		camp1 = new Campus();
		camp1.setName("PES");
		camp1.setCity("Bangalore");
		camp1.setAddress("Ring Road");
		camp1.setEstablishedDate(LocalDate.now());
		
		camp2 = new Campus();
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
	public void findCampusByCityTest_Success() {
		when(campRepo.findByCity("Bangalore")).thenReturn(campCity);
		List<Campus> result = campService.findCampusByCity("Bangalore");
		Assertions.assertNotNull(result);
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(campCity.size(), result.size());
		Assertions.assertEquals(campCity.get(0).getName(), result.get(0).getName());
	}

	@Test
	public void deleteCampusByIdTest_Success() {
		doNothing().when(campRepo).deleteById("103");
		campService.deleteCampusById("103");
		verify(campRepo, times(1)).deleteById("103");
	}

}
