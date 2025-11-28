package com.example.quick.quick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = "com.example.quick")
@EnableJpaRepositories(basePackages = "com.example.quick.quick.repository")
public class QuickApplication {

	public static void main(String[] args) {
		System.out.println("Spring web");
		
		
		SpringApplication.run(QuickApplication.class, args);
		
		
	}

}
