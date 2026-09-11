package com.learning.college.GatewayService.security;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.learning.college.GatewayService.dto.Users;

public class UserPrinciple implements UserDetails {

	Users user;
	
	public UserPrinciple() { }
	
	public UserPrinciple(Users user) {
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(user.getRole()));
	}

	@Override
	public @Nullable String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUsername();
	}

}
