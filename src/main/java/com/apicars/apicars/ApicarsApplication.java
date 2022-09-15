package com.apicars.apicars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EntityScan(basePackages = "com.apicars.models")
@ComponentScan(basePackages = "com.apicars.resources")
@ComponentScan(basePackages = "com.apicars.repositories")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class ApicarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApicarsApplication.class, args);
	}

}
