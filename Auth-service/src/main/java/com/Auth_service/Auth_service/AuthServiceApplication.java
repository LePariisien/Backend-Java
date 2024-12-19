package com.Auth_service.Auth_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootApplication
// @EnableJpaRepositories(basePackages = "com.Auth_service.Repository")
// @EntityScan(basePackages = "com.Auth_service.Entity")
public class AuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthServiceApplication.class, args);
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
	}

}
