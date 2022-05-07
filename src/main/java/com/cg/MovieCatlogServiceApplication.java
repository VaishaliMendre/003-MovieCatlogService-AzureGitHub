package com.cg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication

public class MovieCatlogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieCatlogServiceApplication.class, args);
		System.out.println("movie ");
	}

}
