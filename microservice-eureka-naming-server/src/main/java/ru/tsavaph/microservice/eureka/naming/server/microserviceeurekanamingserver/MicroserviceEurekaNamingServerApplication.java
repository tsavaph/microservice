package ru.tsavaph.microservice.eureka.naming.server.microserviceeurekanamingserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MicroserviceEurekaNamingServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceEurekaNamingServerApplication.class, args);
	}

}
