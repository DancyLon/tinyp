package com.eqchu.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.eqchu.*")
public class EqchuProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(EqchuProjectApplication.class, args);
	}

}
