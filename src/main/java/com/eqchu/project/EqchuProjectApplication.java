package com.eqchu.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.equchu.project.*")
public class EqchuProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EqchuProjectApplication.class, args);
	}

}
