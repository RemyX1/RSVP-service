package com.company.rsvpbacking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RsvpBackingApplication {

	public static void main(String[] args) {
		SpringApplication.run(RsvpBackingApplication.class, args);
	}

}
