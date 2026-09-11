package com.learning.college.UsersManagementSystem;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableDiscoveryClient
@EnableTransactionManagement
public class UsersManagementSystemApplication /*implements CommandLineRunner, ApplicationRunner*/{

	public static void main(String[] args) {
		SpringApplication.run(UsersManagementSystemApplication.class, args);
	}

	/*
	@Override
	public void run(ApplicationArguments args) throws Exception {
			System.out.println("Application Runner");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Command Runner");
		
	}
	*/

}
