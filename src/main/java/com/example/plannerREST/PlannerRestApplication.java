package com.example.plannerREST;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class PlannerRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PlannerRestApplication.class, args);
	}


}
