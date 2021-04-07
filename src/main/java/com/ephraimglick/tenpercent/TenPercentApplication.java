package com.ephraimglick.tenpercent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class TenPercentApplication {

	@RequestMapping("/")
	public String home() {
		return "Hello 10% Time!";
	}

	public static void main(String[] args) {
		SpringApplication.run(TenPercentApplication.class, args);
	}

}
