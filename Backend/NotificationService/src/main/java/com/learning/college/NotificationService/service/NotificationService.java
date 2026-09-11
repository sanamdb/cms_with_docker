package com.learning.college.NotificationService.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.college.NotificationService.dto.Notification;

@Service
public class NotificationService {
	
	@Autowired
	private SimpleNotificationManager notifManager;

	public void sendNotification(Notification notification) {
		notifManager.SendNotification(notification);
	}

}
