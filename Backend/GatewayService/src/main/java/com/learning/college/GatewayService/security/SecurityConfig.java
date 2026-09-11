package com.learning.college.GatewayService.security;

import java.net.http.HttpHeaders;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestHeaderRequestMatcher;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	
	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider(myUserDetailsService);
		auth.setPasswordEncoder(new BCryptPasswordEncoder(12));
		return auth;
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		RequestMatcher version1Matcher = new AndRequestMatcher(
			    new RegexRequestMatcher("/users/user/.*", HttpMethod.GET.name()),
			    new RequestHeaderRequestMatcher("X-VERSION-API", "1")
		);
		
		http.csrf(customizer -> customizer.disable())
			.authorizeHttpRequests(request -> 
					request.requestMatchers("/secure").authenticated()
							.requestMatchers("/users/api/v1/user").permitAll()
							.requestMatchers("/users/api/v1/otp/validate").permitAll()
							.requestMatchers("/auth/api/v1/login").permitAll()
							.requestMatchers("/college/api/v1/faq").permitAll()
							.requestMatchers("/college/api/v1/event").permitAll()
							.requestMatchers(HttpMethod.POST, "/student/api/v1/student").permitAll()
							.requestMatchers(HttpMethod.POST, "/college/api/v1/campus").hasRole("ADMIN")
							.requestMatchers(HttpMethod.GET, "/college/api/v1/admin/profile/**").hasRole("ADMIN")
							.requestMatchers(HttpMethod.PUT, "/college/api/v1/campus/**").hasRole("ADMIN")
							.requestMatchers(HttpMethod.DELETE, "/college/api/v1/campus/**").hasRole("ADMIN")
							.requestMatchers(HttpMethod.GET, "/college/api/v1/campus/**").permitAll()
							.requestMatchers(HttpMethod.POST, "/college/api/v1/department").hasRole("ADMIN")
							.requestMatchers(HttpMethod.GET, "/college/api/v1/department/**").permitAll()
							.requestMatchers(HttpMethod.POST, "/college/api/v1/campus/department").hasRole("ADMIN")
							.requestMatchers(HttpMethod.GET, "/college/api/v1/campus/department/**").permitAll()
							.requestMatchers(HttpMethod.GET, "/users/api/v1/user/active/**").hasAnyRole("STUDENT", "ADMIN")
							.requestMatchers(version1Matcher).permitAll()
							.requestMatchers(HttpMethod.GET, "/users/user/**").hasAnyRole("STUDENT", "ADMIN")
							.requestMatchers(HttpMethod.POST, "/notification/api/v1/email").hasRole("ADMIN")
							.requestMatchers(HttpMethod.POST, "/college/api/v1/batch").hasRole("ADMIN")
							.requestMatchers(HttpMethod.PUT, "/college/api/v1/batch").hasRole("ADMIN")
							.requestMatchers(HttpMethod.GET, "/college/api/v1/batch/**").permitAll()
							.requestMatchers(HttpMethod.POST, "/student/api/v1/apply").hasRole("STUDENT")
							.requestMatchers(HttpMethod.POST, "/question/api/v1/question").hasRole("PROFESSOR")
							.requestMatchers(HttpMethod.PUT, "/question/api/v1/question/**").hasRole("PROFESSOR")
							.requestMatchers(HttpMethod.GET, "/question/api/v1/question/**").permitAll()
							.requestMatchers(HttpMethod.DELETE, "/question/api/v1/question/**").hasRole("PROFESSOR")
							.requestMatchers(HttpMethod.POST, "/exam/api/v1/examination").hasRole("ADMIN")
							.requestMatchers(HttpMethod.GET, "/exam/api/v1/examination/**").hasRole("STUDENT")
							.requestMatchers(HttpMethod.POST, "/exam/api/v1/submit").hasRole("STUDENT")
							.requestMatchers(HttpMethod.POST, "/exam/api/v1/calculate/result").hasRole("ADMIN")
							.requestMatchers(HttpMethod.POST, "/exam/api/v1/student/result").hasRole("STUDENT")
							.requestMatchers(HttpMethod.POST, "/college/api/v1/admission").hasRole("ADMIN")
							.requestMatchers(HttpMethod.POST, "/student/api/v1/student/profile/picture").permitAll()
							.requestMatchers(HttpMethod.GET, "/college/api/v1/admission/**").hasRole("STUDENT")
							.requestMatchers(HttpMethod.GET, "/student/api/v1/student/**").hasRole("STUDENT")
							.requestMatchers(HttpMethod.GET, "/student/api/v1/apply").hasRole("STUDENT")
							.requestMatchers(HttpMethod.GET, "/exam/api/v1/examination/schedule/**").hasRole("STUDENT")
							
							/*
						    .requestMatchers("/admin").hasRole("Admin")	
								//.requestMatchers("/hello").permitAll()
								.requestMatchers("/college/**").permitAll()
								.requestMatchers("/users/**").permitAll()
								.requestMatchers("/notification/**").permitAll()
								.requestMatchers("/auth/**").permitAll()
								.requestMatchers("/student/**").hasRole("STUDENT") */
				)
			.httpBasic(Customizer.withDefaults())
			.sessionManagement(session -> 
				session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
			.oauth2Client(Customizer.withDefaults())
			.exceptionHandling(exception -> exception
		        .authenticationEntryPoint(new CustomAuthenticationEntryPoint()) // Handles 401
		        .accessDeniedHandler(new CustomAccessDeniedHandler())          // Handles 403
		    );
		
		return http.build();
		
	}
	
	/*
	@Bean
	public UserDetailsService userDetailsService() {
		
		UserDetails user1 = User.withDefaultPasswordEncoder()
								.username("sana")
								.password("Sana@123")
								.build();
		
		UserDetails user2 = User.withDefaultPasswordEncoder()
								.username("sakku")
								.password("Sakku@123")
								.build();
		
		return new InMemoryUserDetailsManager(Arrays.asList(user1, user2));
		
	}*/

}
