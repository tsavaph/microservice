package ru.tsavaph.microservice.example.forex.microserviceforexservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MicroserviceForexServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceForexServiceApplication.class, args);
	}

}
