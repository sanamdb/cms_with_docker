package com.learning.college.CollegeManagement.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.learning.college.CollegeManagement.dto.AdmissionRepository;
import com.learning.college.CollegeManagement.dto.Notification;
import com.learning.college.CollegeManagement.dto.StudentProfileResponseDTO;
import com.learning.college.CollegeManagement.entity.Admission;
import com.learning.college.CollegeManagement.entity.Campus;
import com.learning.college.CollegeManagement.entity.Department;
import com.learning.college.CollegeManagement.kafkaProducer.SendNotificationEvent;
import com.learning.college.CollegeManagement.rest.StudentProfileRest;

@Service
public class AdmissionService {
	
	@Autowired
	private AdmissionRepository admissionRepo;
	
	@Autowired
	private SendNotificationEvent sendNotifKafka;
	
	private static final Logger logger = LoggerFactory.getLogger(AdmissionService.class);
	
	public Admission processAdmission(Admission admission) {
		logger.info("Before save");
		Admission response =  admissionRepo.save(admission);
		logger.info("Before Kafka");
		sendNotifKafka.sendNotificationToKafkaEvent(response);
		logger.info("Admission Service");
		return response;
	}

	public Admission getAdmissionData(String username) {
		return admissionRepo.findByStudentUsername(username);
	}

}
