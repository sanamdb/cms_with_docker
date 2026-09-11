package com.learning.college.GatewayService.config;

import org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions;
import org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.RequestPredicates;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

@Configuration
public class GatewayRoutingConfig {
	
	@Bean
    public RouterFunction<ServerResponse> collegeManagementRoute() {
        return GatewayRouterFunctions.route("college_management_route")
                .route(RequestPredicates.path("/college/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("COLLEGE-MANAGEMENT"))
                .build();
    }
	
	@Bean
    public RouterFunction<ServerResponse> usersManagementRoute() {
        return GatewayRouterFunctions.route("user_management_route")
                .route(RequestPredicates.path("/users/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("USER-MANAGEMENT-SYSTEM"))
                .build();
    }

	@Bean
    public RouterFunction<ServerResponse> notificationRoute() {
        return GatewayRouterFunctions.route("notification_route")
                .route(RequestPredicates.path("/notification/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("NOTIFICATION-SERVICE"))
                .build();
    }
	
	@Bean
    public RouterFunction<ServerResponse> studentRoute() {
        return GatewayRouterFunctions.route("student_route")
                .route(RequestPredicates.path("/student/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("STUDENT-SERVICE"))
                .build();
    }
	
	@Bean
    public RouterFunction<ServerResponse> questionRoute() {
        return GatewayRouterFunctions.route("question_route")
                .route(RequestPredicates.path("/question/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("QUESTION-SERVICE"))
                .build();
    }
	
	@Bean
    public RouterFunction<ServerResponse> examRoute() {
        return GatewayRouterFunctions.route("exam_route")
                .route(RequestPredicates.path("/exam/**"), HandlerFunctions.http())
                .filter(LoadBalancerFilterFunctions.lb("EXAM-SERVICE"))
                .build();
    }
}
