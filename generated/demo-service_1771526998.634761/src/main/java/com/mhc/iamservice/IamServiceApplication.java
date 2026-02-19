package com.mhc.iamservice;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IamServiceApplication implements CommandLineRunner {

	@Value("${spring.data.mongodb.uri}")
	private String mongoUri;

	public static void main(String[] args) {
		SpringApplication.run(IamServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		System.out.println("==== MongoDB URI Spring Boot is using: " + mongoUri + " ====");
	}

}
