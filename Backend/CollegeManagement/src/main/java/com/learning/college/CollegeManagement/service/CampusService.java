package com.learning.college.CollegeManagement.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.learning.college.CollegeManagement.dto.CampusRequestDTO;
import com.learning.college.CollegeManagement.entity.Campus;
import com.learning.college.CollegeManagement.repository.CampusRepo;
import com.learning.college.CollegeManagement.util.CampusUtils;

@Service
public class CampusService {
	
	@Autowired
	private CampusRepo repo;

	public Campus saveCampus(Campus campusReq) {
		//campusReq.setId(new CampusUtils().getCampusId());
		campusReq.setEstablishedDate(LocalDate.now());
		return repo.save(campusReq);
	}

	public Page<Campus> findAllCampus(int page, int size) {
		Pageable pageRequest = PageRequest.of(page, size);
		return repo.findAll(pageRequest);
	}

	public Campus findCampusById(String id) {
		 return repo.findById(id).orElse(null);
	}

	public Campus updateCampusById(CampusRequestDTO campus, Campus campusRes, String id) {
		campusRes.setEstablishedDate(LocalDate.now());
		campusRes.setName(campus.getName());
		campusRes.setAddress(campus.getAddress());
		campusRes.setCity(campus.getCity());
		return repo.save(campusRes);
		
	}

	public List<Campus> findCampusByCity(String city) {
		return repo.findByCity(city);
	}

	public void deleteCampusById(String id) {
		repo.deleteById(id);	
	}

}
