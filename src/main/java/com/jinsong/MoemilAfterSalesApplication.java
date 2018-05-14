package com.jinsong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MoemilAfterSalesApplication extends SpringBootServletInitializer{
	


	public static void main(String[] args) {
		SpringApplication.run(MoemilAfterSalesApplication.class, args);
	}
	
	@GetMapping("/hello")
	public String hello() {
		
		return "hello test";
	}
}
