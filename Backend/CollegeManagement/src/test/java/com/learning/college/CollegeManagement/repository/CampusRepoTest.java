package com.learning.college.CollegeManagement.repository;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.learning.college.CollegeManagement.entity.Campus;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

@DataJpaTest
public class CampusRepoTest {

	@Autowired
	private CampusRepo campRepo;
	
	Campus camp1;
	Campus camp2;
	Campus camp3;
	
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
		camp3.setName("Thakur College");
		camp3.setCity("Mumbai");
		camp3.setAddress("Navi mumbai");
		camp3.setEstablishedDate(LocalDate.now());
		
		campRepo.save(camp1);
		campRepo.save(camp2);
		campRepo.save(camp3);
	}
	
	@AfterEach
	public void tearDown()  {
		campRepo.deleteAll();
		campRepo = null;
		camp1 = null;
		camp2 = null;
		camp3 = null;
	}
	
	@Test
	public void findByCityTest_Success() {
		List<Campus> result = campRepo.findByCity("Bangalore");
		Assertions.assertFalse(result.isEmpty());
		Assertions.assertEquals(2, result.size());
		Assertions.assertEquals("PES", result.get(0).getName());
	}
}
