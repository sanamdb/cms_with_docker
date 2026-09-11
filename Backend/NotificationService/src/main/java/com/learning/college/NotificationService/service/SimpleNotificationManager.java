package com.learning.college.NotificationService.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.learning.college.NotificationService.dto.Notification;
import com.learning.college.NotificationService.interfaceContract.NotificationManager;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class SimpleNotificationManager implements NotificationManager {

	private JavaMailSender mailSender;
	private SimpleMailMessage templateMessage;
	
	@Autowired
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Autowired
	public void setTemplateMessage(SimpleMailMessage templateMessage) {
		this.templateMessage = templateMessage;
	}


	@Override
	public void SendNotification(Notification notification) {
		try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setTo(notification.getEmail());
            helper.setSubject(notification.getSubject());
            
            helper.setText(notification.getMessage(), true);
            
            if (notification.getCc() != null && !notification.getCc().isEmpty()) {
                helper.setCc(notification.getCc());
            }
            helper.setFrom("no.reply.learning.college@gmail.com");
            helper.setReplyTo("enquiry.learning.college@gmail.com");

            mailSender.send(mimeMessage);
        } catch (Exception ex) {
            System.err.println("Failed to send email: " + ex.getMessage());
        }
		
	}

}
