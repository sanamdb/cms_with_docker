package com.learning.college.CollegeManagement.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.college.CollegeManagement.dto.CampusDepartmentDetailsResponseDTO;
import com.learning.college.CollegeManagement.entity.CampusDepartment;
import com.learning.college.CollegeManagement.repository.CampusDepartmentRepo;

@Service
public class CampusDepartmentService {
	
	@Autowired
	private CampusDepartmentRepo repo;

	public CampusDepartment createCampusDepartment(CampusDepartment payload) {
		
		//payload.setId(new CampusDepartmentUtils().generateId(payload.getCampusId(), payload.getDepartmentId()));
		payload.setStatus("Active");
		payload.setEstablishedDate(LocalDate.now());
		return repo.save(payload);
	}

	public List<CampusDepartmentDetailsResponseDTO> findCampusDepartmentDetails(String status) {
		return repo.getCampusDepartmentDetails(status);
	}

	public List<CampusDepartment> findCampusWithLesserFees(long fees) {
		return repo.getLesserFeesCampus(fees);
	}

	public List<CampusDepartment> getDepartmentOfferByCampus(String campusId) {
		return repo.findByCampusId(campusId);
	}

}
