package com.learning.college.CollegeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.learning.college.CollegeManagement.entity.Batch;
import com.learning.college.CollegeManagement.repository.BatchRepository;

@Service
public class BatchService {

	@Autowired
	private BatchRepository batchRepo;

	public Batch createBatch(Batch payload) {
		Batch availability = batchRepo.findByAcademicYear(payload.getAcademicYear());
		if(availability != null)
			return availability;
		return batchRepo.save(payload);
	}

	public List<Batch> findAllBatch() {
		return batchRepo.findAll();
	}

	public Batch findById(String id) {
		return batchRepo.findById(id).orElse(null);
	}

	@Cacheable(value = "batch", key = "#year")
	public Batch findByYear(String year) {
		return batchRepo.findByAcademicYear(year);
	}

	@CacheEvict(value = "batch", key = "#availibility.academicYear")
	public void updateBatch(Batch availibility) {
		batchRepo.save(availibility);		
	}
	
	
}
