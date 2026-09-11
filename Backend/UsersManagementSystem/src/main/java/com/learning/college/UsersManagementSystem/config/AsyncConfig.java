package com.learning.college.UsersManagementSystem.config;

import java.util.concurrent.Executor;

import org.apache.tomcat.util.threads.ThreadPoolExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig {
	
	@Bean
	public Executor executor() {
		ThreadPoolTaskExecutor thread = new ThreadPoolTaskExecutor();
		thread.setCorePoolSize(8);
		thread.setMaxPoolSize(16);
		thread.setThreadNamePrefix("Email Thread");
		return thread;		
	}

}
