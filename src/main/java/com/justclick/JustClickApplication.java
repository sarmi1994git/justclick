package com.justclick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class JustClickApplication {

	public static void main(String[] args) {
		SpringApplication.run(JustClickApplication.class, args);
	}

}
