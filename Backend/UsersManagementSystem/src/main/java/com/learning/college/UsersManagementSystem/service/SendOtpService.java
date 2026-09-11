package com.learning.college.UsersManagementSystem.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.learning.college.UsersManagementSystem.dto.Notification;
import com.learning.college.UsersManagementSystem.dto.UserRequestDTO;
import com.learning.college.UsersManagementSystem.rest.NotificationService;

@Service
public class SendOtpService {
	
	@Autowired
	private NotificationService notifService;

	@Async
	public CompletableFuture<String> sendOtpToMailer(UserRequestDTO user, String otp) {
		Notification notification = new Notification();
		notification.setEmail(user.getEmail());
		notification.setSubject("Your Sign-Up OTP Verification");
		String message = "<p>Dear Customer,</p>"
		        + "<p>Please find your sign-up OTP below to complete your registration with us:</p>"
		        + "<h2 style='color: #2e6c80;'>" + otp + "</h2>"
		        + "<p>This OTP is valid for a limited time. Please do not share it with anyone.</p>"
		        + "<br>"
		        + "<p>Warm regards,<br>"
		        + "<strong>The Support Team</strong><br>"
		        + "College Management System</p>";

		notification.setMessage(message);
		notification.setCc("enquiry.learning.college@gmail.com");
		notifService.notification(notification);
		String ref = String.valueOf((int) (Math.random()*10000000));
		return CompletableFuture.completedFuture(ref);
		
		}
}
