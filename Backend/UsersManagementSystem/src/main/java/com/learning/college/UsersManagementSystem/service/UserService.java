package com.learning.college.UsersManagementSystem.service;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.hc.core5.concurrent.CompletedFuture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learning.college.UsersManagementSystem.dto.OtpRequestDto;
import com.learning.college.UsersManagementSystem.dto.UserOtpValidateResponse;
import com.learning.college.UsersManagementSystem.dto.UserRequestDTO;
import com.learning.college.UsersManagementSystem.dto.UserResponseDTO;
import com.learning.college.UsersManagementSystem.entity.Otp;
import com.learning.college.UsersManagementSystem.entity.Users;
import com.learning.college.UsersManagementSystem.exception.OtpValidationException;
import com.learning.college.UsersManagementSystem.exception.UserGenericException;
import com.learning.college.UsersManagementSystem.repository.OtpRepo;
import com.learning.college.UsersManagementSystem.repository.UsersRepo;
import com.learning.college.UsersManagementSystem.utils.GenerateOTP;
import com.learning.college.UsersManagementSystem.utils.PasswordEncoder;

@Service
public class UserService {
	
	@Autowired
	private UsersRepo userRepo;
	
	@Autowired
	private OtpRepo otpRepo;
	
	@Autowired
	private SendOtpService otpSendService;
	
	public CompletableFuture<Otp> sendOtp(UserRequestDTO user) {
		
		String otp = new GenerateOTP().generateOTP();
		
		CompletableFuture<String> ref = otpSendService.sendOtpToMailer(user, otp);		
		Otp otpReq = new Otp();
		otpReq.setUsername(user.getUsername());
		otpReq.setEmail(user.getEmail());
		otpReq.setStatus("UNVERIFIED ");
		otpReq.setOtp(otp);
		otpReq.setIssueAt(LocalDateTime.now());
		otpReq.setExpireAt(LocalDateTime.now().plusMinutes(20));
		try {
			otpReq.setOtpReferenceNo(ref.get());
		}  catch (Exception e) {}
		
		Otp res = otpRepo.save(otpReq);
		
		return CompletableFuture.completedFuture(res);
		
	}

	public Users registration(UserRequestDTO user) {
		
		String hashPass = new PasswordEncoder().encode(user.getPassword());
	
		Users userReq = new Users();
		userReq.setId(user.getUsername());
		userReq.setUsername(user.getUsername());
		userReq.setPassword(hashPass);
		userReq.setEmail(user.getEmail());
		userReq.setPhoneNo(user.getPhoneNo());
		userReq.setStatus("INACTIVE");
		userReq.setRole("ROLE_STUDENT");
		userReq.setRegisterAt(LocalDateTime.now());
		
		return userRepo.save(userReq);
		
	}

	@Transactional
	public UserOtpValidateResponse validateOtp(OtpRequestDto otp) {
		
		UserOtpValidateResponse responses = new UserOtpValidateResponse();
		
		Otp otpres = otpRepo.findByUsername(otp.getUsername());
		Users user = userRepo.findByUsername(otp.getUsername());
		
		if(otpres == null)
			return null;
		
		if(user == null)
			return null;
		
		if(LocalDateTime.now().isAfter(otpres.getExpireAt())) {
			responses.setStatus(otpres.getStatus());
			responses.setUsername(otpres.getUsername());
			responses.setMessage("Otp has been expired");
			return responses;
		}
		
		if(!(otp.getOtp().equals(otpres.getOtp()))) {
			responses.setStatus(otpres.getStatus());
			responses.setUsername(otpres.getUsername());
			responses.setMessage("Otp is invalid");
			return responses;
		}
		
		if(otp.getOtp().equals(otpres.getOtp())) {	
			otpres.setStatus("VERIFIED");
			otpRepo.save(otpres);
			user.setStatus("ACTIVE");
			userRepo.save(user);
			
			responses.setUsername(otpres.getUsername());
			responses.setStatus(otpres.getStatus());
			responses.setMessage("Otp has been successfully verified");
		}
		
		return responses;
		
	}

	public Users checkAvailibility(String username) {
		return userRepo.findByUsername(username);
	}

	public Users findActiveUser(String username) {
		return userRepo.findByUsernameAndStatusEquals(username, "ACTIVE");
	}

	public Users findAllUser(String username) {
		return userRepo.findByUsername(username);
	}

}
