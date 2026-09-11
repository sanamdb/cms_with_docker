package com.learning.college.CollegeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.college.CollegeManagement.entity.Memorandum;
import com.learning.college.CollegeManagement.repository.MemorandumRepo;

@Service
public class MemorandumService {
	
	@Autowired
	private MemorandumRepo repo;

	public Memorandum post(Memorandum payload) {
		return repo.save(payload);
	}

	public List<Memorandum> get() {
		return repo.findAll();
	}

}
