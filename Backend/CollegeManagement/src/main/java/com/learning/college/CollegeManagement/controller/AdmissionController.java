package com.learning.college.CollegeManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.CollegeManagement.dto.AdmissionRequestDTO;
import com.learning.college.CollegeManagement.dto.AdmissionResponseDTO;
import com.learning.college.CollegeManagement.entity.Admission;
import com.learning.college.CollegeManagement.service.AdmissionService;

@RestController
@RequestMapping("college/api/v1")
public class AdmissionController {
	
	@Autowired
	private AdmissionService admissionService;

	@PostMapping("admission")
	public ResponseEntity<AdmissionResponseDTO> admission(@RequestBody AdmissionRequestDTO requestDto) {
		Admission admission = new Admission();
		admission.setStudentUsername(requestDto.getStudentUsername());
		admission.setCampusId(requestDto.getCampusId());
		admission.setDepartmentId(requestDto.getDepartmentId());
		admission.setExamId(requestDto.getExamId());
		admission.setBatch(requestDto.getBatch());
		
		Admission response = admissionService.processAdmission(admission);
		
		AdmissionResponseDTO finalRes = new AdmissionResponseDTO();
		finalRes.setId(response.getId());
		finalRes.setCampusId(response.getCampusId());
		finalRes.setDepartmentId(response.getDepartmentId());
		finalRes.setExamId(response.getExamId());
		finalRes.setBatch(response.getBatch());
		finalRes.setStudentUsername(response.getStudentUsername());
		return new ResponseEntity<>(finalRes, HttpStatus.OK);
	}
	
	@GetMapping("admission/{username}")
	public ResponseEntity<AdmissionResponseDTO> getAdmissionDetails(@PathVariable("username") String username) {
		Admission admission =  admissionService.getAdmissionData(username);
		AdmissionResponseDTO response = new AdmissionResponseDTO();
		response.setId(admission.getId());
		response.setCampusId(admission.getCampusId());
		response.setDepartmentId(admission.getDepartmentId());
		response.setExamId(admission.getExamId());
		response.setBatch(admission.getBatch());
		response.setStudentUsername(admission.getStudentUsername());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
}
