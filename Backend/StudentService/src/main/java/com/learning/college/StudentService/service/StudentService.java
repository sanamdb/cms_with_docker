package com.learning.college.StudentService.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learning.college.StudentService.entity.AdmissionApplication;
import com.learning.college.StudentService.entity.Student;
import com.learning.college.StudentService.repository.AdmissionApplicationRepository;
import com.learning.college.StudentService.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository stdRepo;
	
	@Autowired
	private AdmissionApplicationRepository admissionApplicationRepo;

	public Student createPersonalInfo(Student std) {
		return stdRepo.save(std);
	}

	public AdmissionApplication submitAdmissionForm(AdmissionApplication payload) {
		
		AdmissionApplication formStatus = admissionApplicationRepo.findByUsernameAndDepartmentIdAndBatch(payload.getUsername(), payload.getDepartmentId(), payload.getBatch());
		
		AdmissionApplication request = new AdmissionApplication();
		
		if(formStatus == null) {
			request.setUsername(payload.getUsername());
			request.setDepartmentId(payload.getDepartmentId());
			request.setBatch(payload.getBatch());
			request.setCreateDateTime(LocalDateTime.now());
			request.setUpdateDateTime(LocalDateTime.now());
			request.setRemarks("NEW");
			request.setStatus(payload.getStatus());
			return admissionApplicationRepo.save(request);			
		}
		
		if(formStatus != null && formStatus.getStatus().equals("SAVED")) {
			request.setUsername(payload.getUsername());
			request.setDepartmentId(payload.getDepartmentId());
			request.setBatch(payload.getBatch());
			request.setCreateDateTime(formStatus.getCreateDateTime());
			request.setUpdateDateTime(LocalDateTime.now());
			request.setRemarks("MODIFIED");
			request.setStatus(payload.getStatus());
			return admissionApplicationRepo.save(request);	
		}
				
		if(formStatus != null && formStatus.getStatus().equals("SUBMITTED")) {
			formStatus.setRemarks("NO-ACTION");
			return formStatus;
		}
		
		return formStatus;
	}

	public String uploadProfilePic(MultipartFile file, String userId) throws IOException {
		Student std = stdRepo.findById(userId).orElse(null);
		if(std == null)
			return "Kindly complete profile before uploading picture";
		
		String encodedPic = Base64.getEncoder().encodeToString(file.getBytes());
		std.setProfilePic(encodedPic);
		stdRepo.save(std);
		return "Uploaded Successfully";
	}

	public Student getProfile(String id) {
		return stdRepo.findById(id).orElse(null);
	}

	public List<AdmissionApplication> findAppliedApplications(String userId) {
		return admissionApplicationRepo.findByUsername(userId);
	}

}
