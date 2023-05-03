package ru.tsavaph.microservice.example.telegram.microservicetelegrambot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients("ru.tsavaph.microservice.example.telegram.microservicetelegrambot.proxy")
@EnableDiscoveryClient
@ComponentScan(basePackages = {
		"ru.tsavaph.microservice.example.telegram.microservicetelegrambot.bot",
		"org.telegram.telegrambots"
})
public class MicroserviceTelegramBotApplication {

	public static void main(String[] args) {
		SpringApplication.run(MicroserviceTelegramBotApplication.class, args);
	}

}
