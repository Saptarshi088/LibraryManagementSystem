package com.saptarshi.SecurityPractice;

import com.saptarshi.SecurityPractice.model.User;
import com.saptarshi.SecurityPractice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@SpringBootApplication
public class SecurityPracticeApplication {
	public static void main(String[] args) {
		SpringApplication.run(SecurityPracticeApplication.class, args);
	}

}
