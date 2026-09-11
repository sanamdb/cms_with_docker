package com.learning.college.UsersManagementSystem.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.learning.college.UsersManagementSystem.dto.Notification;

import io.github.resilience4j.retry.annotation.Retry;

@Service
public class NotificationService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Retry(name = "notificationServiceRetry", fallbackMethod = "notificationFallback")
	public ResponseEntity<Notification> notification(Notification notification) {
		String url = "http://NOTIFICATION-SERVICE/notification/api/v1/email";
		System.out.println("Notification method retry");
		return restTemplate.postForEntity(url, notification, Notification.class);
		
	}
	
	// Fallback Method (matching signature + Throwable)
    public ResponseEntity<Notification> notificationFallback(Notification notification, Throwable throwable) {
        System.err.println("Notification-Service failed after retries! Cause: " + throwable.getMessage());
        
        // Return a safe default response or log failure
        return ResponseEntity.ok(notification); 
    }

}
