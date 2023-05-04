package com.retroBoard.retroBoard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication
public class RetroBoardApplication {

	public static void main(String... args) {
		SpringApplication.run(RetroBoardApplication.class, args);
	}

}
