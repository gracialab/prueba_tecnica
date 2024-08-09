package com.camilo.springboot.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class BibliotecaLibrosApplication {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaLibrosApplication.class, args);
	}

}
