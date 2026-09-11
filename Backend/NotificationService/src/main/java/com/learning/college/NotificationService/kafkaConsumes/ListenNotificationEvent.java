package com.learning.college.NotificationService.kafkaConsumes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.learning.college.NotificationService.dto.Notification;
import com.learning.college.NotificationService.service.NotificationService;

@Service
public class ListenNotificationEvent {
	
	@Autowired
	private NotificationService notifServ;
	
	private static final Logger logger = LoggerFactory.getLogger(ListenNotificationEvent.class);
	
	@KafkaListener(topics = "college", groupId = "notification-group")
	public void SendNotificationViaEmail(Notification notification) {
		logger.info("Notification data "+ notification.getEmail());
		notifServ.sendNotification(notification);
		logger.info("after notification send");
	}
}
