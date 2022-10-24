package com.example.baggage_calculator_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BaggageCalculatorSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(BaggageCalculatorSpringApplication.class, args);
	}
//	@GetMapping("/hello")
//	public String Hello(){
//		return "Hello World!";
//	}
}
