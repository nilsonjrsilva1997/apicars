package com.apicars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@EntityScan(basePackages = "com.apicars.apicars.models")
@ComponentScan(basePackages = "com.apicars.apicars.resources")
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })

public class ApicarsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApicarsApplication.class, args);
	}

}
