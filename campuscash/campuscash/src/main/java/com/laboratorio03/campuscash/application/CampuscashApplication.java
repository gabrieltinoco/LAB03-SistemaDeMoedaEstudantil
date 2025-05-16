package com.laboratorio03.campuscash.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"com.laboratorio03"})
@EnableJpaRepositories("com.laboratorio03.campuscash.repositories")
@EntityScan(basePackages = "com.laboratorio03.campuscash.models")
public class CampuscashApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampuscashApplication.class, args);
	}

}
