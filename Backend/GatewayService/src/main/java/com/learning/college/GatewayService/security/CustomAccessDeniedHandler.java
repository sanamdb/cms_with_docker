package com.learning.college.GatewayService.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.learning.college.GatewayService.dto.AuthErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import java.io.IOException;
import java.time.LocalDateTime;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        AuthErrorResponseDTO errorDTO = new AuthErrorResponseDTO(
        		HttpServletResponse.SC_FORBIDDEN,
                "Forbidden",
                "Access Denied: You do not have the required permissions to access this endpoint.",
                request.getRequestURI()
        );

        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        response.getWriter().write(mapper.writeValueAsString(errorDTO));
    }
}
