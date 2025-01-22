package com.example.oders;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(OdersApplication.class, args);
	}

	@Bean
	public ModelMapper ModelMapper() {
		return new ModelMapper();
	}
}
