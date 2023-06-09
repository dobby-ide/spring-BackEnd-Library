package com.rest_api.fs14backend;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//TO DO

/**
 * 1 add possibility to update for all the controllers (and in services)
 */
@SpringBootApplication
@EnableJpaRepositories("com.rest_api.fs14backend.repositories")
@EntityScan("com.rest_api.fs14backend.entities")
public class Fs14BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(Fs14BackendApplication.class, args);
	}

}
