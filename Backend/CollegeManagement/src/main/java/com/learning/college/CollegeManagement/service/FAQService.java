package com.learning.college.CollegeManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.college.CollegeManagement.entity.FAQ;
import com.learning.college.CollegeManagement.repository.FAQRepo;

@Service
public class FAQService {

	@Autowired
	private FAQRepo faqRepo;
	
	public List<FAQ> getAllFaq() {
		return faqRepo.findAll();
	}

	public FAQ createFaq(FAQ faq) {
		return faqRepo.save(faq);
	}

}
