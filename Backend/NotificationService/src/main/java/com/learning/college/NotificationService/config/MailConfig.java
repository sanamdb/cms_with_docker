package com.learning.college.NotificationService.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

@Configuration
public class MailConfig {

	@Bean
    public SimpleMailMessage templateMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        // You can pre-set default values here if you want, 
        // or just leave it clean since you override them in your manager
        message.setFrom("no.reply.learning.college@gmail.com"); 
        return message;
    }
}
