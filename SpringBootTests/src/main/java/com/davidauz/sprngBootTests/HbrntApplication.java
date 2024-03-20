package com.davidauz.sprngBootTests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// This is needed only for the Spring Boot Tests,
// otherwise it cries like a baby because
// "Could not detect default configuration classes for test"
@SpringBootApplication
public class HbrntApplication {

	public static void main(String[] args) {
		SpringApplication.run(HbrntApplication.class, args);
	}

}
