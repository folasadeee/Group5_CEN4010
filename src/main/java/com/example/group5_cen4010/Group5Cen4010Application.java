package com.example.group5_cen4010;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Group5Cen4010Application {

	public static void main(String[] args) {
		SpringApplication.run(Group5Cen4010Application.class, args);
	}

	@GetMapping
	public String hello() {
		return "Hello, world!";
	}

}
