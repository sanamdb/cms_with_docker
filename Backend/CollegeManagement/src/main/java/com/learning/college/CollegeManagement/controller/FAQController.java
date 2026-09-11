package com.learning.college.CollegeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.CollegeManagement.entity.FAQ;
import com.learning.college.CollegeManagement.service.FAQService;


@RestController
@RequestMapping("college/api/v1")
public class FAQController {
	
	@Autowired
	private FAQService faqService;
	
	@GetMapping("faq")
	public ResponseEntity<List<FAQ>> getAllFaq() {
		List<FAQ> response = faqService.getAllFaq();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping("faq")
	public ResponseEntity<FAQ> createFaq(@RequestBody FAQ faq) {
		FAQ response = faqService.createFaq(faq);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

}
