package com.jeisonruckert.bikescanoas;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BikescanoasApplication implements CommandLineRunner {
	
	public static void main(String[] args) {
		SpringApplication.run(BikescanoasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
