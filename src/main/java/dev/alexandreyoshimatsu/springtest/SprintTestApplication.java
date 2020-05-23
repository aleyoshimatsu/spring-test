package dev.alexandreyoshimatsu.springtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"dev.alexandreyoshimatsu"})
public class SprintTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SprintTestApplication.class, args);
	}

}
