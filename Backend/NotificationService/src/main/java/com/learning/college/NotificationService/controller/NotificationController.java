package com.learning.college.NotificationService.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.college.NotificationService.dto.Notification;
import com.learning.college.NotificationService.service.NotificationService;

@RestController
@RequestMapping("notification/api/v1")
public class NotificationController {
	
	@Autowired
	private NotificationService notificationService;

	@PostMapping("email")
	public ResponseEntity<Notification> sendNotification(@RequestBody Notification notification) {
		notificationService.sendNotification(notification);
		return new ResponseEntity<>(notification, HttpStatus.ACCEPTED);
	}
}
