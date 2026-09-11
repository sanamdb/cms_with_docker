package com.learning.college.GatewayService.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.learning.college.GatewayService.dto.AuthErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        AuthErrorResponseDTO errorDTO = new AuthErrorResponseDTO(
                HttpServletResponse.SC_UNAUTHORIZED,
                "Unauthorized",
                "Invalid username or password. Please verify your credentials.",
                request.getRequestURI()
        );

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        response.getWriter().write(mapper.writeValueAsString(errorDTO));
    }
}