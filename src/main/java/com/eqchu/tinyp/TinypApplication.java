package com.eqchu.tinyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.eqchu.*")
public class TinypApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinypApplication.class, args);
	}

}
