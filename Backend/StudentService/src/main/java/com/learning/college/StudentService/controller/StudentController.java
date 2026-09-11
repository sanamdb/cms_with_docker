package com.learning.college.StudentService.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.learning.college.StudentService.dto.ApplyForAdmissionRequestDTO;
import com.learning.college.StudentService.dto.ApplyForAdmissionResponseDTO;
import com.learning.college.StudentService.dto.BatchResponseDTO;
import com.learning.college.StudentService.dto.DepartmentResponseDTO;
import com.learning.college.StudentService.dto.PhotoResponseDTO;
import com.learning.college.StudentService.dto.StudentPersonalInformationRequestDTO;
import com.learning.college.StudentService.dto.StudentPersonalInformationResponseDTO;
import com.learning.college.StudentService.dto.StudentProfileResponseDTO;
import com.learning.college.StudentService.entity.AdmissionApplication;
import com.learning.college.StudentService.entity.Student;
import com.learning.college.StudentService.exception.ApplyFormSubmissionException;
import com.learning.college.StudentService.exception.StudentGenericException;
import com.learning.college.StudentService.exception.UsernameNotFoundException;
import com.learning.college.StudentService.restClient.BatchRestClient;
import com.learning.college.StudentService.restClient.DepartmentRestClient;
import com.learning.college.StudentService.service.StudentService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("student/api/v1")
public class StudentController {
	
	@Autowired
	private StudentService stdService;
	
	@Autowired
	private DepartmentRestClient deptRestClient;
	
	@Autowired
	private BatchRestClient batchRestClient;
	
	@PostMapping("student")
	public ResponseEntity<StudentPersonalInformationResponseDTO> personalStudentInformation(@RequestBody StudentPersonalInformationRequestDTO stdPerDto, HttpServletRequest request) throws UsernameNotFoundException, StudentGenericException {
		
		String userId = request.getHeader("userId");
		
		if(userId == null || userId.equals(""))
			throw new UsernameNotFoundException("Please login", "Please try after login");
		
		Student std = new Student();
		std.setId(userId);
		std.setFirstName(stdPerDto.getFirstName());
		std.setLastName(stdPerDto.getLastName());
		std.setEmail(stdPerDto.getEmail());
		std.setAlternateEmail(stdPerDto.getAlternateEmail());
		std.setPhoneNo(stdPerDto.getPhoneNo());
		std.setAlternatePhoneNo(stdPerDto.getAlternatePhoneNo());
		std.setFullAddress(stdPerDto.getFullAddress());
		std.setCity(stdPerDto.getCity());
		std.setPincode(stdPerDto.getPincode());
		std.setState(stdPerDto.getState());
		std.setDob(stdPerDto.getDob());
		
		Student stdRes = stdService.createPersonalInfo(std);
		
		if(stdRes == null)
			throw new StudentGenericException("Profile couldn't be completed", "Profile submission couldn't be completed try after soem time");
		
		StudentPersonalInformationResponseDTO response = new StudentPersonalInformationResponseDTO();
		response.setFirstName(stdRes.getFirstName());
		response.setLastName(stdRes.getLastName());
		response.setEmail(stdRes.getEmail());
		response.setAlternateEmail(stdRes.getAlternateEmail());
		response.setPhoneNo(stdRes.getPhoneNo());
		response.setAlternatePhoneNo(stdRes.getAlternatePhoneNo());
		response.setDob(stdRes.getDob());
		response.setFullAddress(stdRes.getFullAddress());
		response.setCity(stdRes.getCity());
		response.setPincode(stdRes.getPincode());
		response.setState(stdRes.getState());
		
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PostMapping("apply")
	public ResponseEntity<ApplyForAdmissionResponseDTO> applyForAdmissionInDepartment(@RequestBody ApplyForAdmissionRequestDTO applyRequestDto, HttpServletRequest request) throws UsernameNotFoundException, ApplyFormSubmissionException {
		
		String userId = request.getHeader("userId");
		if(userId == null)
			throw new UsernameNotFoundException("Please login", "Please try after login");
		
		DepartmentResponseDTO deptRes=null;
		try {
			deptRes = deptRestClient.getDepartmentById(applyRequestDto.getDepartmentId());
		} catch (Exception e) {
			throw new ApplyFormSubmissionException("Department not found", "You are applying to wrong department please check department");
		}
		if(deptRes == null)
			throw new ApplyFormSubmissionException("Department not found", "You are applying to wrong department please check department");
		
		BatchResponseDTO batchRes = null;
		try {
			batchRes = batchRestClient.getBatchByYear(applyRequestDto.getBatch());
		} catch (Exception e) {
			throw new ApplyFormSubmissionException("Batch not found", "Batch is not open to apply for admission");
		}
		if(batchRes == null || batchRes.getId() == null)
			throw new ApplyFormSubmissionException("Batch not found", "Batch is not open to apply for admission");
		
		int currentYear = LocalDate.now().getYear();
		int batchYear = Integer.parseInt( applyRequestDto.getBatch());
		
		System.out.println(currentYear + " : "+batchYear);
		
		if(currentYear > batchYear)
			throw new ApplyFormSubmissionException("Batch is closed", "Back year application not allowed");
		
		AdmissionApplication payload = new AdmissionApplication();
		payload.setDepartmentId(applyRequestDto.getDepartmentId());
		payload.setBatch(applyRequestDto.getBatch());
		switch(applyRequestDto.getAction()) {
			case "SAVE": payload.setStatus("SAVED");break;
			case "SUBMIT": payload.setStatus("SUBMITTED");break;
			case "WITHDRAW": payload.setStatus("WITHDRAWN");break;
		}
		payload.setUsername(userId);
		
		AdmissionApplication serviceResponse = stdService.submitAdmissionForm(payload);
		
		if(serviceResponse == null)
			throw new ApplyFormSubmissionException("Form submission failed", "Admission form couldn't be saved/submitted");
		
		ApplyForAdmissionResponseDTO finalResponse = new ApplyForAdmissionResponseDTO();
		finalResponse.setId(serviceResponse.getId());
		finalResponse.setDepartmentId(serviceResponse.getDepartmentId());
		finalResponse.setBatch(serviceResponse.getBatch());
		finalResponse.setCreateDateTime(serviceResponse.getCreateDateTime());
		finalResponse.setUpdateDateTime(serviceResponse.getUpdateDateTime());
		finalResponse.setStatus(serviceResponse.getStatus());
		if(serviceResponse.getStatus().equals("SUBMITTED"))
			finalResponse.setRemarks("Your application has been submitted");
		if(serviceResponse.getStatus().equals("WITHDRAWN"))
			finalResponse.setRemarks("Your application has been withdrawn");
		if(serviceResponse.getRemarks().equals("NEW") && serviceResponse.getStatus().equals("SAVED"))
			finalResponse.setRemarks("Your application has been saved");
		if(serviceResponse.getRemarks().equals("MODIFIED") && serviceResponse.getStatus().equals("SAVED")) {
			finalResponse.setRemarks("Your application has been modified");
		}
		if(serviceResponse.getRemarks().equals("NO-ACTION"))
			finalResponse.setRemarks("Your have already submitted the application, no action can be made");
		
		return new ResponseEntity<>(finalResponse, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("student/profile/picture")
	public ResponseEntity<PhotoResponseDTO> uploadProfilePicture(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws UsernameNotFoundException, IOException {
		
		String userId = request.getHeader("userId");
		
		if(userId == null || userId.equals(""))
			throw new UsernameNotFoundException("Please login", "Please try after login");
		
		if(file.isEmpty()) {
			return new ResponseEntity<>(new PhotoResponseDTO("File is empty"), HttpStatus.BAD_REQUEST);
		}
		String result = stdService.uploadProfilePic(file, userId);
		return new ResponseEntity<>(new PhotoResponseDTO(result), HttpStatus.OK);
	}
	
	@GetMapping("student/{id}")
	public ResponseEntity<StudentProfileResponseDTO> getProfile(@PathVariable("id") String id) throws StudentGenericException {
		Student response = stdService.getProfile(id);
		if(response == null)
			throw new StudentGenericException("Not Found", "Your are not my student");
		
		StudentProfileResponseDTO finalResponse = new StudentProfileResponseDTO();
		finalResponse.setFirstName(response.getFirstName());
		finalResponse.setLastName(response.getLastName());
		finalResponse.setEmail(response.getEmail());
		finalResponse.setAlternateEmail(response.getAlternateEmail());
		finalResponse.setPhoneNo(response.getPhoneNo());
		finalResponse.setAlternatePhoneNo(response.getAlternatePhoneNo());
		finalResponse.setDob(response.getDob());
		finalResponse.setCity(response.getCity());
		finalResponse.setFullAddress(response.getFullAddress());
		finalResponse.setPincode(response.getPincode());
		finalResponse.setProfilePic(response.getProfilePic());
		finalResponse.setState(response.getState());
		
		return new ResponseEntity<>(finalResponse, HttpStatus.OK);
	}
	
	@GetMapping("apply")
	public ResponseEntity<List<ApplyForAdmissionResponseDTO>> getAppliedApplication(HttpServletRequest request) throws UsernameNotFoundException {
		
		String userId = request.getHeader("userId");
		if(userId == null)
			throw new UsernameNotFoundException("Please login", "Please try after login");
		
		List<AdmissionApplication> applications = stdService.findAppliedApplications(userId);
		List<ApplyForAdmissionResponseDTO> response = new ArrayList<>();
		for(AdmissionApplication application: applications) {
			ApplyForAdmissionResponseDTO admission = new ApplyForAdmissionResponseDTO();
			admission.setId(application.getId());
			admission.setDepartmentId(application.getDepartmentId());
			admission.setBatch(application.getBatch());
			admission.setCreateDateTime(application.getCreateDateTime());
			admission.setUpdateDateTime(application.getUpdateDateTime());
			admission.setStatus(application.getStatus());
			admission.setRemarks(application.getRemarks());
			response.add(admission);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
