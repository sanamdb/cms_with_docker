package com.learning.college.CollegeManagement.kafkaProducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.learning.college.CollegeManagement.dto.Notification;
import com.learning.college.CollegeManagement.dto.StudentProfileResponseDTO;
import com.learning.college.CollegeManagement.entity.Admission;
import com.learning.college.CollegeManagement.entity.Campus;
import com.learning.college.CollegeManagement.entity.Department;
import com.learning.college.CollegeManagement.rest.StudentProfileRest;
import com.learning.college.CollegeManagement.service.CampusService;
import com.learning.college.CollegeManagement.service.DepartmentService;

@Service
public class SendNotificationEvent {
	
	@Autowired
	private KafkaTemplate<String, Notification> kafka;
	
	@Autowired
	private DepartmentService deptService;
	
	@Autowired
	private CampusService campService;
	
	@Autowired
	private StudentProfileRest stdProfileRest;
	
	private static final Logger logger = LoggerFactory.getLogger(SendNotificationEvent.class);

	@Async
	public void sendNotificationToKafkaEvent(Admission response) {
		logger.info("Kafka send method called");
		Department dept = deptService.findDepartmentById(response.getDepartmentId());
		Campus camp = campService.findCampusById(response.getCampusId());
		StudentProfileResponseDTO stdProfile = stdProfileRest.getStudentProfile(response.getStudentUsername());
		logger.info("stdProfile" + stdProfile.getAlternateEmail());
		Notification notification = new Notification();
		notification.setEmail(stdProfile.getEmail());
		notification.setCc(stdProfile.getAlternateEmail());
		notification.setSubject("Congratulations! Your Admission is Officially Confirmed 🎉");
		
		String message = "<p>Dear " + stdProfile.getFirstName() + " " + stdProfile.getLastName() + ",</p>"
		        + "<p>Congratulations! We are thrilled to inform you that your admission has been successfully completed.</p>"
		        + "<p>You are now officially enrolled in the <strong>" + dept.getName() + " (" + dept.getDescription() + ")</strong> department at our <strong>" + camp.getName() + ", " + camp.getCity() + "</strong> campus.</p>"
		        + "<p>We are excited to welcome you to our academic community and look forward to supporting your educational journey.</p>"
		        + "<br>"
		        + "<p>Warm regards,<br>"
		        + "<strong>The Admissions Team</strong><br>"
		        + "College Management System</p>";
		
		notification.setMessage(message);
		/*
		 * Topic - college
		 * key - notification
		 * value - Notification DTO
		 *  */
		kafka.send("college", "notification", notification);
		
		logger.info("after kafka send");
	}

}
